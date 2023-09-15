package mmk.com.sg.controller;

import java.io.IOException;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.data.entity.StatementChgComment;
import mmk.com.sg.dto.model.FileUploadResponse;
import mmk.com.sg.service.AnnexServiceImpl;
import mmk.com.sg.service.StatementChgComService;
import mmk.com.sg.utility.FileUploadUtil;

@RestController
@RequestMapping("api/v1")
public class FileUploadController {
	
	@Autowired
    private AnnexServiceImpl serviceImpl;
	
	@PostMapping(path="/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(
            @RequestParam("file") MultipartFile multipartFile)
                    throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
         
        String filecode = FileUploadUtil.saveFile(fileName, multipartFile);
         
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUri("/downloadFile/" + filecode);
         
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@PostMapping("/uploadFile/annex")
    public ResponseEntity<FileUploadResponse> uploadFileAnnex(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("annex") String annex,
            @RequestParam("annexNo") Long annexNo,
            @RequestParam("recordingId") Long recordingId,
            @RequestParam("description") String description
            
    		)
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
         
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
