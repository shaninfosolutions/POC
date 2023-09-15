package mmk.com.sg.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.data.entity.Statement;
import mmk.com.sg.dto.model.FileUploadResponse;
import mmk.com.sg.service.AnnexServiceImpl;
import mmk.com.sg.utility.FileUploadUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class C2ISAnnexController {
	
	
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
	
	// Delete operation
    @DeleteMapping("/annex/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteAnnexById(@PathVariable("id") Long id)
    {
    	serviceImpl.deleteAnnexFileById(id);
        return "Deleted Successfully";
    }

}
