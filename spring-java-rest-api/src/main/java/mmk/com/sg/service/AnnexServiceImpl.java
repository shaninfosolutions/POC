package mmk.com.sg.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.data.repository.AnnexFileRepository;

@Service
public class AnnexServiceImpl implements AnnexService{
	

	@Autowired
	private AnnexFileRepository repository;

	@Override
	public AnnexFile saveAnnexFile(AnnexFile annexFile) {
		return repository.save(annexFile);
	}

	@Override
	public List<AnnexFile> fetchAnnexFileList() {
		
		return (List<AnnexFile>) repository.findAll();
	}

	@Override
	public AnnexFile updateAnnexFile(AnnexFile annexFile, Long annexFileId) {

		AnnexFile depDB = repository.findById(annexFileId).get();
		 
	    if (Objects.nonNull(annexFile.getAnnex()) && !"".equalsIgnoreCase(annexFile.getAnnex())) {
	        depDB.setAnnex(annexFile.getAnnex());
	    }
	    
	    if (Objects.nonNull(annexFile.getAnnexNo()) ) {
	        depDB.setAnnexNo(annexFile.getAnnexNo());
	    }
	    
	    if (Objects.nonNull(annexFile.getDescription()) && !"".equalsIgnoreCase(annexFile.getDescription())) {
	        depDB.setDescription(annexFile.getDescription());
	    }
	    
	    if (Objects.nonNull(annexFile.getFileSize())) {
	        depDB.setFileSize(annexFile.getFileSize());
	    }
	    
	    return repository.save(depDB); 
	}

	@Override
	public void deleteAnnexFileById(Long annexFileId) {
		repository.deleteById(annexFileId);
		
	}
	
	@Override
	public  AnnexFile findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public AnnexFile findByFileName(String fileName) {
		// TODO Auto-generated method stub
		return repository.findByFileName(fileName);
	}

	@Override
	public List<AnnexFile> findByRecordingId(Long recordingId) {
		return (List<AnnexFile>)repository.findByRecordingId(recordingId);
	}

	@Override
	public AnnexFile updateAnnexFile(String annex, Long annexNo, String description, Long annexFileId) {
		AnnexFile depDB = repository.findById(annexFileId).get();
		 
	    if (Objects.nonNull(annex) && !"".equalsIgnoreCase(annex)) {
	        depDB.setAnnex(annex);
	    }
	    
	    if (Objects.nonNull(annexNo) ) {
	        depDB.setAnnexNo(annexNo);
	    }
	    
	    if (Objects.nonNull(description) && !"".equalsIgnoreCase(description)) {
	        depDB.setDescription(description);
	    }

	    
	    return repository.save(depDB); 
	}
	
	@Override
	public AnnexFile updateSignature(String officierSignature, 
			String witnessSignature, String interpreterSignature, Long annexFileId) {
		AnnexFile depDB = repository.findById(annexFileId).get();
		 
	    if (Objects.nonNull(officierSignature) && !"".equalsIgnoreCase(officierSignature)) {
	        depDB.setOfficierSignature(officierSignature);
	    }
	    
	    if (Objects.nonNull(witnessSignature) && !"".equalsIgnoreCase(witnessSignature)) {
	        depDB.setWitnessSignature(witnessSignature);
	    }
	    
	    if (Objects.nonNull(interpreterSignature) && !"".equalsIgnoreCase(interpreterSignature)) {
	        depDB.setInterpreterSignature(interpreterSignature);
	    }

	    
	    return repository.save(depDB); 
	}


}
