package mmk.com.sg.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mmk.com.sg.data.entity.StatementChgSuggestion;

@Repository
public interface StatementChgSugRepository extends CrudRepository<StatementChgSuggestion, Long> {

	List<StatementChgSuggestion> findByRecordingId(Long recordingId);
	
	StatementChgSuggestion findBySuggestionThreadId(String threadId);
	

}
