package mmk.com.sg.service;

import java.util.List;

import mmk.com.sg.data.entity.DigitalStatementPdf;

public interface DigitalStatementPdfService {
	// save operation
	DigitalStatementPdf saveDigitalStatementPdf(DigitalStatementPdf digitalStatementPdf);
		
		// update operation
	DigitalStatementPdf updateDigitalStatementPdf(DigitalStatementPdf digitalStatementPdf, Long digitalStatementPdfId);
		
		 // delete operation
	    void deleteDigitalStatementPdfId(Long statementPdfId);
	    
	    DigitalStatementPdf findById(Long digitalStatementPdfId);
	    
	    List<DigitalStatementPdf> findByRecordingId(Long recordingId);
}
