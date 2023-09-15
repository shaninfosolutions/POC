package mmk.com.sg.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.data.entity.StatementPdf;
import mmk.com.sg.dto.model.FileUploadResponse;
import mmk.com.sg.service.StatementPdfServiceImpl;
import mmk.com.sg.utility.FileUploadUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class StatementPdfController {
	@Autowired
    private StatementPdfServiceImpl serviceImpl;
	
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
	    	serviceImpl.deleteStatementPdfById(id);
	        return "Deleted Successfully";
	    }
	 
	 
}
