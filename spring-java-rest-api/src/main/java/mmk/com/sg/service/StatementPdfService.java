package mmk.com.sg.service;

import java.util.List;

import mmk.com.sg.data.entity.StatementPdf;

public interface StatementPdfService {
	// save operation
	StatementPdf saveStatementPdf(StatementPdf statementPdf);
	
	// update operation
	StatementPdf updateStatementPdf(StatementPdf statementPdf, Long statementPdfId);
	
	 // delete operation
    void deleteStatementPdfById(Long statementPdfId);
    
    StatementPdf findById(Long statementPdfId);
    
    List<StatementPdf> findByRecordingId(Long recordingId);
    
			
}
