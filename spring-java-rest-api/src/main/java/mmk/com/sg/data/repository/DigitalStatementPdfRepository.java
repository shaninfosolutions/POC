package mmk.com.sg.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mmk.com.sg.data.entity.DigitalStatementPdf;



public interface DigitalStatementPdfRepository extends CrudRepository<DigitalStatementPdf, Long>{
	List<DigitalStatementPdf> findByRecordingIdOrderByIdAsc(Long recordingId);
}
