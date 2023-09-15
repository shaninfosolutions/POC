package mmk.com.sg.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mmk.com.sg.data.entity.DigitalStatementPdf;
import mmk.com.sg.data.repository.DigitalStatementPdfRepository;

@Service
public class DigitalStatementServiceImpl implements DigitalStatementPdfService{
	
	@Autowired
	private DigitalStatementPdfRepository repository;
	
	@Override
	public DigitalStatementPdf saveDigitalStatementPdf(DigitalStatementPdf digitalStatementPdf) {
		
		return repository.save(digitalStatementPdf);
	}

	@Override
	public DigitalStatementPdf updateDigitalStatementPdf(DigitalStatementPdf digitalStatementPdf, Long digitalStatementPdfId) {

		DigitalStatementPdf depDB = repository.findById(digitalStatementPdfId).get();
		 
	    if (Objects.nonNull(digitalStatementPdf.getFilePath()) && !"".equalsIgnoreCase(digitalStatementPdf.getFilePath())) {
	        depDB.setFilePath(digitalStatementPdf.getFilePath());
	    }
	    
	  //  if (Objects.nonNull(statementPdf.fil) && !"".equalsIgnoreCase(statementPdf.getFilePath())) {
	     //   depDB.setFilePath(statementPdf.getFilePath());
	  // }
	    
	    
		return repository.save(depDB);
	}

	@Override
	public void deleteDigitalStatementPdfId(Long digitalStatementPdfId) {
		repository.deleteById(digitalStatementPdfId);
		
	}

	@Override
	public DigitalStatementPdf findById(Long statementPdfId) {
		
		return repository.findById(statementPdfId).get();
	}

	@Override
	public List<DigitalStatementPdf> findByRecordingId(Long recordingId) {
		
		return  (List<DigitalStatementPdf>)repository.findByRecordingIdOrderByIdAsc(recordingId);
	}

}
