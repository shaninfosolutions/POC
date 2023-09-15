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

import mmk.com.sg.data.entity.DigitalStatementPdf;
import mmk.com.sg.dto.model.FileUploadResponse;
import mmk.com.sg.service.DigitalStatementServiceImpl;
import mmk.com.sg.utility.AsposeDigitalSign;
import mmk.com.sg.utility.FileUploadUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class DigitalStatementPdfController {
	
	@Autowired
    private  DigitalStatementServiceImpl serviceImpl;
	
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
	    	serviceImpl.deleteDigitalStatementPdfId(id);
	        return "Deleted Successfully";
	    }
	 

}
