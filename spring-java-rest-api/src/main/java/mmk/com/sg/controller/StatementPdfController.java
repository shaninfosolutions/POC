package mmk.com.sg.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.data.entity.DigitalStatementPdf;
import mmk.com.sg.data.entity.Statement;
import mmk.com.sg.data.entity.StatementPdf;
import mmk.com.sg.dto.model.FileUploadResponse;
import mmk.com.sg.service.StatementPdfServiceImpl;
import mmk.com.sg.utility.FileDownloadUtil;
import mmk.com.sg.utility.FileUploadUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class StatementPdfController {
	@Autowired
    private StatementPdfServiceImpl serviceImpl;
	
	@Value("${statement.pdf.file.input}")
	private String statementPdfFileInput;
	
	
	 @GetMapping("statementpdf/recordingid/{recordingId}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public List<StatementPdf>  findAllStatementPdfByRecordingId(
			 @PathVariable Long recordingId
			 ) {
		 return serviceImpl.findByRecordingId(recordingId);
	 }
	 
	 @PostMapping("/statementpdf/uploadFile")
	    public ResponseEntity<FileUploadResponse> uploadFileSatement(
	            @RequestParam("file") MultipartFile multipartFile,
	            @RequestParam("filepath") String filepath,
	            @RequestParam("recordingId") Long recordingId)
	                    throws IOException {
	         
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

	        long size = multipartFile.getSize();
	        StatementPdf statementPdf=new StatementPdf();
	        
	        statementPdf.setRecordingId(recordingId);
	        
	        List<StatementPdf> list=serviceImpl.findByRecordingId(recordingId);
	        long verno=1;
	        if(list!=null && !list.isEmpty()) {
	        	verno=verno+list.size();
	        }
	       
	        statementPdf.setFileVerNo(verno);
	        statementPdf.setFilePath(filepath);
	        statementPdf.setFileName("Version "+verno+"_"+fileName);
	         
	        String filecode = FileUploadUtil.saveFileDigitalInput("Version "+verno+"_"+fileName, multipartFile);
	        StatementPdf saveStatementPdf=serviceImpl.saveStatementPdf(statementPdf);

	        FileUploadResponse response = new FileUploadResponse();
	        response.setId(saveStatementPdf.getId());
	        response.setFileName("Version "+verno+"_"+fileName);
	        response.setSize(size);
	        response.setDownloadUri( filecode);
	        response.setAnnex(null);
	        response.setAnnexNo(null);
	        response.setDescription(null);
	        response.setRecordingId(saveStatementPdf.getRecordingId());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 
	// Delete operation
	    @DeleteMapping("/statementpdf/{id}")
	    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	    public String deleteStatementById(@PathVariable("id") Long id)
	    {
	    	
	    	StatementPdf statementPdf=serviceImpl.findById(id);
	    	if(statementPdf==null) {
	    		// return new ResponseEntity<>("File not found in DB, please try again", HttpStatus.NOT_FOUND);
	    		return "File not found, please kindly check";
	    	}
	    	
	    	 File myObj = new File(statementPdfFileInput+statementPdf.getFileName());
	    	 if (myObj.delete()) { 
	    		 serviceImpl.deleteStatementPdfById(id);
	    	 }else {
	    		 return "File not found, please kindly check";
	    	  } 
	    	
	        return "Deleted Successfully";
	        
	    }
	    
	    
	    @GetMapping("/downloadFile/statement/{id}")
	    public ResponseEntity<?> downloadFile(@PathVariable("id") Long statementId)  {
	    	FileDownloadUtil downloadUtil = new FileDownloadUtil();
	        
	    	StatementPdf statementPdf=serviceImpl.findById(statementId);
	    	
	    	if(statementPdf==null) {
	    		 return new ResponseEntity<>("File not found in DB, please try again", HttpStatus.NOT_FOUND);
	    	}
	    	
	        Resource resource = null;
	        resource = downloadUtil.getFileAsResourceByFileName(statementPdf.getFileName(),
	        		statementPdfFileInput);

	        if (resource == null) {
	            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
	        }
	         
	        String contentType = "application/octet-stream";
	        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
	         
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
	                .body(resource);    
	    }
	    
	    
	   @RequestMapping(value="/downloadFile/statement/", method=RequestMethod.GET)
	    public void downloadFileById(
	    		@RequestParam(value="statementId", required=false) Long statementId,
	    		HttpServletResponse response)  {
	    	FileDownloadUtil downloadUtil = new FileDownloadUtil();
	        
	    	StatementPdf statementPdf=serviceImpl.findById(statementId);
	    	
	    	if(statementPdf==null) {
	    		System.out.print("File not found");
	    	}
	    	
	        Resource resource = null;
	        resource = downloadUtil.getFileAsResourceByFileName(statementPdf.getFileName(),
	        		statementPdfFileInput);
	        
	        if(resource!=null) {
	        try {
			InputStream content=resource.getInputStream();
			
			
	        
	        String contentType = "application/octet-stream";
	        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";
	      
	        response.setContentType(contentType);
	        response.setHeader(statementPdfFileInput, headerValue);
	        FileCopyUtils.copy(content, response.getOutputStream());
	        content.close();
	        
	        response.flushBuffer();
	        
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	      //  String contentType = "application/octet-stream";
	       // String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";       
	        
	    }
	   


	private Exception Exceptions() {
		// TODO Auto-generated method stub
		return null;
	}
	 
	 
}
