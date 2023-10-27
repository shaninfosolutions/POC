package mmk.com.sg.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mmk.com.sg.data.entity.StatementChgComment;

@Repository
public interface StatementChgComRepository extends CrudRepository<StatementChgComment, Long>{
	List<StatementChgComment> findByRecordingId(Long recordingId);
	
	StatementChgComment findByCommentThreadId(String commentThreadId);
	

	
	StatementChgComment findByCommentThreadIdAndCommentId(String commentThreadId,String commentId);
	
}
