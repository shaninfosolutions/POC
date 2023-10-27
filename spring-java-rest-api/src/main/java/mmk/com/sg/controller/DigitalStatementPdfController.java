package mmk.com.sg.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mmk.com.sg.data.entity.DigitalStatementPdf;
import mmk.com.sg.data.entity.StatementPdf;
import mmk.com.sg.dto.model.FileUploadResponse;
import mmk.com.sg.service.DigitalStatementServiceImpl;
import mmk.com.sg.service.StatementPdfServiceImpl;
import mmk.com.sg.utility.AsposeDigitalSign;
import mmk.com.sg.utility.FileDownloadUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class DigitalStatementPdfController {
	
	@Autowired
    private  DigitalStatementServiceImpl serviceImpl;
	
	@Value("${statement.pdf.file.output}")
	private String digitalPdfFileOutput;
	
	@Autowired
	private StatementPdfServiceImpl statementPdfserviceImpl;
	
	@GetMapping("digitalstmtpdf/recordingid/{recordingId}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public List<DigitalStatementPdf>  findAllStatementPdfByRecordingId(
			 @PathVariable Long recordingId
			 ) {
		 return serviceImpl.findByRecordingId(recordingId);
	 }
	
	 @PostMapping("/digitalstmtpdf/sign")
	    public ResponseEntity<FileUploadResponse> signDigitalStatement(
	            @RequestParam("file") MultipartFile multipartFile,
	            @RequestParam("filepath") String filepath,
	            @RequestParam("recordingId") Long recordingId)
	                    throws IOException {
	         
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

	        long size = multipartFile.getSize();
	        DigitalStatementPdf digitalStatementPdf=new DigitalStatementPdf();
	        
	        digitalStatementPdf.setRecordingId(recordingId);
	        
	        List<DigitalStatementPdf> list=serviceImpl.findByRecordingId(recordingId);
	        long verno=1;
	        if(list!=null && !list.isEmpty()) {
	        	verno=verno+list.size();
	        }
	       
	       // String filecode = FileUploadUtil.saveFileDigitalInput("Signed "+verno+"_"+fileName, multipartFile);
	        //To Sign digital signature 
	        String outfilename = AsposeDigitalSign.SignDocument(fileName, verno);
	        
	        digitalStatementPdf.setFileVerNo(verno);
	        digitalStatementPdf.setFilePath(filepath);
	        digitalStatementPdf.setFileName(outfilename);
	        DigitalStatementPdf saveDigitalStatementPdf=serviceImpl.saveDigitalStatementPdf(digitalStatementPdf);

	        FileUploadResponse response = new FileUploadResponse();
	        response.setId(saveDigitalStatementPdf.getId());
	        response.setFileName("Version "+verno+"_"+fileName);
	        response.setSize(size);
	        response.setDownloadUri( outfilename);
	        response.setAnnex(null);
	        response.setAnnexNo(null);
	        response.setDescription(null);
	        response.setRecordingId(saveDigitalStatementPdf.getRecordingId());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 
	 @PostMapping("/signpdf/sign")
	    public ResponseEntity<FileUploadResponse> signDigitalStatementById(
	            @RequestParam("statementId") Long statementId,
	            @RequestParam("filepath") String filepath,
	            @RequestParam("recordingId") Long recordingId)
	                    throws IOException {
		 
		 	StatementPdf statementPdf=statementPdfserviceImpl.findById(statementId);
	         
	        //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 	String fileName=statementPdf.getFileName();

	        //long size = multipartFile.getSize();
		 	long size=0;
	        DigitalStatementPdf digitalStatementPdf=new DigitalStatementPdf();
	        
	        digitalStatementPdf.setRecordingId(recordingId);
	        
	        List<DigitalStatementPdf> list=serviceImpl.findByRecordingId(recordingId);
	        long verno=1;
	        if(list!=null && !list.isEmpty()) {
	        	verno=verno+list.size();
	        }
	       
	       // String filecode = FileUploadUtil.saveFileDigitalInput("Signed "+verno+"_"+fileName, multipartFile);
	        //To Sign digital signature 
	        String outfilename = AsposeDigitalSign.SignDocument(fileName, verno);
	        
	        //if(filepath==null) {
	        	filepath="Digital-Signature-Output";
	        //}
	        
	        digitalStatementPdf.setFileVerNo(verno);
	        digitalStatementPdf.setFilePath(filepath);
	        digitalStatementPdf.setFileName(outfilename);
	        DigitalStatementPdf saveDigitalStatementPdf=serviceImpl.saveDigitalStatementPdf(digitalStatementPdf);

	        FileUploadResponse response = new FileUploadResponse();
	        response.setId(saveDigitalStatementPdf.getId());
	        response.setFileName("Version "+verno+"_"+fileName);
	        response.setSize(size);
	        response.setDownloadUri( outfilename);
	        response.setAnnex(null);
	        response.setAnnexNo(null);
	        response.setDescription(null);
	        response.setRecordingId(saveDigitalStatementPdf.getRecordingId());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 @PostMapping("/digitalstatement/sign")
	    public ResponseEntity<FileUploadResponse> digitalStatementById(
	            @RequestParam("statementId") Long statementId,
	            @RequestParam("filepath") String filepath,
	            @RequestParam("recordingId") Long recordingId)
	                    throws IOException {
		 
		 	StatementPdf statementPdf=statementPdfserviceImpl.findById(statementId);
	         
	        //String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		 	String fileName=statementPdf.getFileName();

	        //long size = multipartFile.getSize();
		 	long size=0;
	        DigitalStatementPdf digitalStatementPdf=new DigitalStatementPdf();
	        
	        digitalStatementPdf.setRecordingId(recordingId);
	        
	        List<DigitalStatementPdf> list=serviceImpl.findByRecordingId(recordingId);
	        long verno=1;
	        if(list!=null && !list.isEmpty()) {
	        	verno=verno+list.size();
	        }
	       
	       // String filecode = FileUploadUtil.saveFileDigitalInput("Signed "+verno+"_"+fileName, multipartFile);
	        //To Sign digital signature 
	        String outfilename = AsposeDigitalSign.SignDocument(fileName, verno);
	        
	        //if(filepath==null) {
	        	filepath="Digital-Signature-Output";
	        //}
	        
	        digitalStatementPdf.setFileVerNo(verno);
	        digitalStatementPdf.setFilePath(filepath);
	        digitalStatementPdf.setFileName(outfilename);
	        DigitalStatementPdf saveDigitalStatementPdf=serviceImpl.saveDigitalStatementPdf(digitalStatementPdf);

	        FileUploadResponse response = new FileUploadResponse();
	        response.setId(saveDigitalStatementPdf.getId());
	        response.setFileName("Version "+verno+"_"+fileName);
	        response.setSize(size);
	        response.setDownloadUri( outfilename);
	        response.setAnnex(null);
	        response.setAnnexNo(null);
	        response.setDescription(null);
	        response.setRecordingId(saveDigitalStatementPdf.getRecordingId());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 @PostMapping("/digitalstmtpdf/signtsr")
	    public ResponseEntity<FileUploadResponse> signDigitalStatementTsr(
	            @RequestParam("file") MultipartFile multipartFile,
	            @RequestParam("filepath") String filepath,
	            @RequestParam("recordingId") Long recordingId)
	                    throws IOException {
	         
	        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

	        long size = multipartFile.getSize();
	        DigitalStatementPdf digitalStatementPdf=new DigitalStatementPdf();
	        
	        digitalStatementPdf.setRecordingId(recordingId);
	        
	        List<DigitalStatementPdf> list=serviceImpl.findByRecordingId(recordingId);
	        long verno=1;
	        if(list!=null && !list.isEmpty()) {
	        	verno=verno+list.size();
	        }
	       
	       // String filecode = FileUploadUtil.saveFileDigitalInput("Signed "+verno+"_"+fileName, multipartFile);
	        //To Sign digital signature 
	        String outfilename = AsposeDigitalSign.SignWithTimeStampServer(fileName, verno);
	        
	        digitalStatementPdf.setFileVerNo(verno);
	        digitalStatementPdf.setFilePath(filepath);
	        digitalStatementPdf.setFileName(outfilename);
	        DigitalStatementPdf saveDigitalStatementPdf=serviceImpl.saveDigitalStatementPdf(digitalStatementPdf);

	        FileUploadResponse response = new FileUploadResponse();
	        response.setId(saveDigitalStatementPdf.getId());
	        response.setFileName(outfilename);
	        response.setSize(size);
	        response.setDownloadUri( outfilename);
	        response.setAnnex(null);
	        response.setAnnexNo(null);
	        response.setDescription(null);
	        response.setRecordingId(saveDigitalStatementPdf.getRecordingId());
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	 
	 
	// Delete operation
	    @DeleteMapping("/digitalstmtpdf/{id}")
	    @ResponseStatus(value = HttpStatus.NO_CONTENT)
	    public String deleteDigitalStatementById(@PathVariable("id") Long id)
	    {
	    	
	    	DigitalStatementPdf digitalstatementPdf=serviceImpl.findById(id);
	    	if(digitalstatementPdf==null) {
	    		// return new ResponseEntity<>("File not found in DB, please try again", HttpStatus.NOT_FOUND);
	    		return "File not found, please kindly check";
	    	}
	    	
	    	 File myObj = new File(digitalPdfFileOutput+digitalstatementPdf.getFileName());
	    	 if (myObj.delete()) { 
	    		 serviceImpl.deleteDigitalStatementPdfId(id);
	    	 }else {
	    		 return "File not found, please kindly check";
	    	  } 
	    	
	    	//serviceImpl.deleteDigitalStatementPdfId(id);
	        return "Deleted Successfully";
	    }
	    
	    
	    @GetMapping("/downloadFile/digitalstmt/{id}")
	    public ResponseEntity<?> downloadFile(@PathVariable("id") Long statementId)  {
	    	FileDownloadUtil downloadUtil = new FileDownloadUtil();
	        
	    	DigitalStatementPdf digitalstatementPdf=serviceImpl.findById(statementId);
	    	
	    	if(digitalstatementPdf==null) {
	    		 return new ResponseEntity<>("File not found in DB, please try again", HttpStatus.NOT_FOUND);
	    	}
	    	
	        Resource resource = null;
	        resource = downloadUtil.getFileAsResourceByFileName(digitalstatementPdf.getFileName(),
	        		digitalPdfFileOutput);

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

}
