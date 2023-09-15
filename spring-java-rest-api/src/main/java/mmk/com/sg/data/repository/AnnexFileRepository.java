package mmk.com.sg.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mmk.com.sg.data.entity.AnnexFile;

public interface AnnexFileRepository extends CrudRepository<AnnexFile, Long>{
	
	AnnexFile findByFileName(String fileName);
	
	List<AnnexFile> findByRecordingId(Long recordingId);
	
	

}
