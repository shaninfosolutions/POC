package mmk.com.sg.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mmk.com.sg.data.entity.AnnexFile;
import mmk.com.sg.data.entity.StatementPdf;
import mmk.com.sg.data.repository.StatementPdfRepository;

@Service
public class StatementPdfServiceImpl implements StatementPdfService {

	@Autowired
	private StatementPdfRepository repository;
	
	@Override
	public StatementPdf saveStatementPdf(StatementPdf statementPdf) {
		
		return repository.save(statementPdf);
	}

	@Override
	public StatementPdf updateStatementPdf(StatementPdf statementPdf, Long statementPdfId) {

		StatementPdf depDB = repository.findById(statementPdfId).get();
		 
	    if (Objects.nonNull(statementPdf.getFilePath()) && !"".equalsIgnoreCase(statementPdf.getFilePath())) {
	        depDB.setFilePath(statementPdf.getFilePath());
	    }
	    
	  //  if (Objects.nonNull(statementPdf.fil) && !"".equalsIgnoreCase(statementPdf.getFilePath())) {
	     //   depDB.setFilePath(statementPdf.getFilePath());
	  // }
	    
	    
		return repository.save(depDB);
	}

	@Override
	public void deleteStatementPdfById(Long statementPdfId) {
		repository.deleteById(statementPdfId);
		
	}

	@Override
	public StatementPdf findById(Long statementPdfId) {
		
		return repository.findById(statementPdfId).get();
	}

	@Override
	public List<StatementPdf> findByRecordingId(Long recordingId) {
		
		return  (List<StatementPdf>)repository.findByRecordingId(recordingId);
	}

}
