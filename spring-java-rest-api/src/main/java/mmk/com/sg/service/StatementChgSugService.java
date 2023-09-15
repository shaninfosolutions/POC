package mmk.com.sg.service;

import java.util.List;

import mmk.com.sg.data.entity.StatementChgSuggestion;

public interface StatementChgSugService {
	
	// save operation
	StatementChgSuggestion saveStatementChgSuggestion(StatementChgSuggestion statement);
		 
		    // read operation
		    List<StatementChgSuggestion> fetchStatementChgSuggestionList();
		 
		    // update operation
		    StatementChgSuggestion updateStatementChgSuggestion(StatementChgSuggestion statement, Long id);
		 
		    // delete operation
		    void deleteStatementChgSuggestionById(Long id);
		    
		    StatementChgSuggestion findById(Long id);
		    
		    List<StatementChgSuggestion> findByRecordingId(Long recordingId);
		    
		    // update operation
		    StatementChgSuggestion updateStatementChgSuggestion(StatementChgSuggestion statement, Long recordingid, String suggestionThreadId);
		 
		    StatementChgSuggestion updateChgSuggestionByThreadId(StatementChgSuggestion suggestion, String threadId);
		   
		    
		    String findBySuggestionThread(String threadId);

}
