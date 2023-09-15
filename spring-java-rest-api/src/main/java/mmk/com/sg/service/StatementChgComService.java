package mmk.com.sg.service;

import java.util.List;

import mmk.com.sg.data.entity.StatementChgComment;

public interface StatementChgComService {

	// save operation
	StatementChgComment saveStatementChgComment(StatementChgComment comment);
			 
			    // read operation
		List<StatementChgComment> fetchStatementChgCommentList();
			 
			    // update operation
		StatementChgComment updateStatementChgComment(StatementChgComment comment, Long id);
		
		StatementChgComment updateStatementChgCommentId(StatementChgComment comment,String threadId,String commentId);
		
		// delete operation
		void deleteStatementChgCommentById(Long id);
			    
		StatementChgComment findById(Long id);
			    
		List<StatementChgComment> findByRecordingId(Long recordingId);
		
		void updateStatementChgComment(StatementChgComment comment, Long recordingId,String commentThreadId);
			    
}
