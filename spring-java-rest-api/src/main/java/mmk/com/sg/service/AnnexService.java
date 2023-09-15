package mmk.com.sg.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import mmk.com.sg.data.entity.AnnexFile;

public interface AnnexService {
	// save operation
		AnnexFile saveAnnexFile(AnnexFile annexFile);
		
		// read operation
	    List<AnnexFile> fetchAnnexFileList();
	 
	    // update operation
	    AnnexFile updateAnnexFile(AnnexFile annexFile, Long annexFileId);
	    
	 // update operation
	    AnnexFile updateAnnexFile( String annex,Long annexNo,String description, 
	            Long annexFileId );
	    
	 
	    // delete operation
	    void deleteAnnexFileById(Long annexFileId);
	    
	    AnnexFile findById(Long id);
	    
	    AnnexFile findByFileName(String fileName);
	    
	    List<AnnexFile> findByRecordingId(Long recordingId);
	    
	    public AnnexFile updateSignature(String officierSignature, 
				String witnessSignature, String interpreterSignature, Long annexFileId);
}
