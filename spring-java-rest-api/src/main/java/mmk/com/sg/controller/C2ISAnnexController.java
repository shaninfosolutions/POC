package mmk.com.sg.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aspose.pdf.BlendMode;
import com.aspose.pdf.CompositingParameters;
import com.aspose.pdf.facades.PdfFileMend;

import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.data.entity.DigitalStatementPdf;
import mmk.com.sg.data.entity.Statement;
import mmk.com.sg.dto.model.FileUploadResponse;
import mmk.com.sg.dto.model.PDFFileSignature;
import mmk.com.sg.service.AnnexServiceImpl;
import mmk.com.sg.utility.FileDownloadUtil;
import mmk.com.sg.utility.FileUploadUtil;
import org.apache.commons.io.IOUtils;
import com.aspose.pdf.Document;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class C2ISAnnexController {
	
	
	@Value("${annex.pdf.file.upload}")
	private String annexPdfFileUpload;
	
	@Value("${annex.pdf.file.signed}")
	private String annexPdfFileSigned;
	
	
	@Autowired
    private AnnexServiceImpl serviceImpl;
	
	 @GetMapping("annex")
	 public List<AnnexFile> findAllAnnexFiles() {
	    	 return this.serviceImpl.fetchAnnexFileList();
	 }
	 
	@GetMapping(value = "/annex/finbyid/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public AnnexFile findById(@PathVariable Long id) {
	return this.serviceImpl.findById(id);
	}
	 
	 @GetMapping("annex/recordingid/{recordingId}")
	 @ResponseStatus(value = HttpStatus.OK)
	 public List<AnnexFile>  findAllAnnexFilesByRecordingId(
			 @PathVariable Long recordingId
			 ) {
		 /*
		 List<AnnexFile> list=this.serviceImpl.findByRecordingId(recordingId);
		 List<FileUploadResponse> response=new ArrayList<FileUploadResponse>();
		 if(list!=null && !list.isEmpty()) {
			 for(AnnexFile a:list) {
				 FileUploadResponse fs = new FileUploadResponse();
				 fs.setId(a.getId());
				 fs.setFileName(a.getFileName());
				 fs.setSize(a.getFileSize());
				 fs.setAnnex(a.getAnnex());
				 fs.setAnnexNo(a.getAnnexNo());
				 fs.setDescription(a.getDescription());
				 fs.setRecordingId(a.getRecordingId());
				 response.add(fs);
			 }
		 }
		 
	    	 return new ResponseEntity<>(response, HttpStatus.OK);*/
		 
		 return serviceImpl.findByRecordingId(recordingId);
	 }
	
	@PostMapping("/annex/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFileAnnex(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("annex") String annex,
            @RequestParam("annexNo") Long annexNo,
            @RequestParam("recordingId") Long recordingId,
            @RequestParam("description") String description)
                    throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        AnnexFile annexFile=new AnnexFile();
        annexFile.setAnnex(annex);
        annexFile.setAnnexNo(annexNo);
        annexFile.setRecordingId(recordingId);
        annexFile.setDescription(description);
        annexFile.setFileName(fileName);
        annexFile.setFileSize(size);
         
        String filecode = FileUploadUtil.saveFileAnnex(fileName, multipartFile);
        AnnexFile saveAnnexFile=serviceImpl.saveAnnexFile(annexFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setId(saveAnnexFile.getId());
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri( filecode);
        response.setAnnex(saveAnnexFile.getAnnex());
        response.setAnnexNo(saveAnnexFile.getAnnexNo());
        response.setDescription(saveAnnexFile.getDescription());
        response.setRecordingId(saveAnnexFile.getRecordingId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	@PostMapping("/annex/addsignature")
    public ResponseEntity<PDFFileSignature> addSignatureFileAnnex(
    		@RequestParam("id") Long id) throws IOException{
		
		AnnexFile annexFile=serviceImpl.findById(id);
		if(annexFile!=null) {
			//Call the Aspose API to add the signature 
			String officierBase64ImgString=annexFile.getOfficierSignature().replace("data:image/png;base64,", "");
			
			//InputStream officerInputStream = IOUtils.toInputStream(officierBase64ImgString, "UTF-8");
			byte[] officierimageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(officierBase64ImgString);
			InputStream officerInputStream = new ByteArrayInputStream(officierimageBytes);
			
			String witnessBase64ImgString=annexFile.getWitnessSignature().replace("data:image/png;base64,", "");
			byte[] witnessimageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(witnessBase64ImgString);
			InputStream witnessInputStream=new ByteArrayInputStream(witnessimageBytes);
			
			String interrupterBased64ImgString=annexFile.getInterpreterSignature().replace("data:image/png;base64,", "");
			byte[] interrupterimageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(interrupterBased64ImgString);
			InputStream interrupterInputStream=new ByteArrayInputStream(interrupterimageBytes);
			
			
			 PdfFileMend mender = new PdfFileMend();
			 
			 Document pdfDocument = new Document(annexPdfFileUpload+ annexFile.getFileName());
			 int pageCount=pdfDocument.getPages().size();
			 pdfDocument.close();
			 // Create PdfFileMend object to add text
		     mender.bindPdf( annexPdfFileUpload+ annexFile.getFileName());
		     
		     CompositingParameters compositingParameters = new CompositingParameters(BlendMode.Multiply);
		        int pageNum = pageCount;
		        int lowerLeftX = 10;
		        int lowerLeftY = 30;
		        int upperRightX = 300;
		        int upperRightY = 50;
		        
		     // mender.addImage(_dataSignatureImage + "Base64_to_Image.png", 1, 10, 10, 300, 50,compositingParameters);
		    mender.addImage(officerInputStream,
				        		pageNum, 
				        		lowerLeftX, 
				        		lowerLeftY,
				        		upperRightX,
				        		upperRightY, compositingParameters); 
		    
		     pageNum = pageCount;
	         lowerLeftX = 10;
	         lowerLeftY = 30;
	         upperRightX = 600;
	         upperRightY = 50;
	         
	         // mender.addImage(_dataSignatureImage + "Base64_to_Image.png", 1, 10, 10, 300, 50,compositingParameters);
			 mender.addImage(witnessInputStream,
					        		pageNum, 
					        		lowerLeftX, 
					        		lowerLeftY,
					        		upperRightX,
					        		upperRightY, compositingParameters); 
			 
			 pageNum = pageCount;
	         lowerLeftX = 10;
	         lowerLeftY = 30;
	         upperRightX = 900;
	         upperRightY = 50;
	         
	      // mender.addImage(_dataSignatureImage + "Base64_to_Image.png", 1, 10, 10, 300, 50,compositingParameters);
			 mender.addImage(interrupterInputStream,
					        		pageNum, 
					        		lowerLeftX, 
					        		lowerLeftY,
					        		upperRightX,
					        		upperRightY, compositingParameters); 
			 mender.save(annexPdfFileSigned + annexFile.getFileName());
			// Close PdfFileMend object
		     mender.close();
		}

		PDFFileSignature response=new PDFFileSignature();
		response.setId(annexFile.getId());
		response.setAnnex(annexFile.getAnnex());
		response.setAnnexNo(annexFile.getAnnexNo());
		response.setRecordingId(annexFile.getRecordingId());
		response.setFileName(annexFile.getFileName());
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	// Update operation
    @PutMapping("/annex")
    @ResponseStatus(value = HttpStatus.OK)
    public AnnexFile updateStatement(
    		@RequestParam("id") Long id,
    		@RequestParam("annex") String annex,
            @RequestParam("annexNo") Long annexNo,
            @RequestParam("description") String description
            )
    {
        return this.serviceImpl.updateAnnexFile(annex, annexNo,description,id) ;
    }
    
 // Update operation
    @PutMapping("/annex/signature")
    @ResponseStatus(value = HttpStatus.OK)
    public AnnexFile updateStatement(
    		@RequestParam("id") Long id,
    		@RequestParam("officialSignature") String officialSignature,
            @RequestParam("witnessSignature") String witnessSignature,
            @RequestParam("interpreterSignature") String interpreterSignature
            )
    {
    	return this.serviceImpl.updateSignature(officialSignature, witnessSignature,interpreterSignature,id) ;
    
    }
    
    
    @GetMapping("/annex/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable("id") Long annexId)  {
    	FileDownloadUtil downloadUtil = new FileDownloadUtil();
        
    	AnnexFile annexFile=serviceImpl.findById(annexId);
    	
    	if(annexFile==null) {
    		 return new ResponseEntity<>("File not found in DB, please try again", HttpStatus.NOT_FOUND);
    	}
    	
        Resource resource = null;
        resource = downloadUtil.getFileAsResourceByFileName(annexFile.getFileName(),
        		annexPdfFileSigned);

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
	
	// Delete operation
    @DeleteMapping("/annex/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteAnnexById(@PathVariable("id") Long id)
    {
    	serviceImpl.deleteAnnexFileById(id);
        return "Deleted Successfully";
    }
    
    
    
    
    

}
