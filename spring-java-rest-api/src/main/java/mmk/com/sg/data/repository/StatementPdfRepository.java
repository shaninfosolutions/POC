package mmk.com.sg.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mmk.com.sg.data.entity.StatementPdf;

@Repository
public interface StatementPdfRepository extends CrudRepository<StatementPdf, Long>{
	
	List<StatementPdf> findByRecordingId(Long recordingId);

}
