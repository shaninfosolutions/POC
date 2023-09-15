package mmk.com.sg.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mmk.com.sg.data.entity.StatementChgSuggestion;
import mmk.com.sg.data.repository.StatementChgSugRepository;

@Service
public class StatementChgSugServiceImpl implements StatementChgSugService {
	
	
@Autowired
private StatementChgSugRepository repository;

	@Override
	public StatementChgSuggestion saveStatementChgSuggestion(StatementChgSuggestion suggestion) {
		
		return repository.save(suggestion);
	}

	@Override
	public List<StatementChgSuggestion> fetchStatementChgSuggestionList() {
		
		return (List<StatementChgSuggestion>) repository.findAll();
	}

	@Override
	public StatementChgSuggestion updateStatementChgSuggestion(StatementChgSuggestion suggestion, Long id) {
		
		StatementChgSuggestion depDB = repository.findById(id).get();
		 
	    if (Objects.nonNull(suggestion.getSuggestionThread()) && !"".equalsIgnoreCase(suggestion.getSuggestionThread())) {
	        depDB.setSuggestionThread(suggestion.getSuggestionThread());
	    }
	    
	    if(Objects.nonNull(suggestion.getRecordingId())) {
	    	 depDB.setRecordingId(suggestion.getRecordingId());
	    }
		return repository.save(depDB);
	}
	
	
	@Override
	public StatementChgSuggestion updateChgSuggestionByThreadId(StatementChgSuggestion suggestion, String threadId) {
		
		StatementChgSuggestion depDB = repository.findBySuggestionThreadId(threadId);
		
		if (Objects.nonNull(suggestion.getSuggestionThreadId()) && !"".equalsIgnoreCase(suggestion.getSuggestionThreadId())) {
	        depDB.setSuggestionThreadId(suggestion.getSuggestionThreadId());
	    }
		
	    if (Objects.nonNull(suggestion.getSuggestionThread()) && !"".equalsIgnoreCase(suggestion.getSuggestionThread())) {
	        depDB.setSuggestionThread(suggestion.getSuggestionThread());
	    }
	    
	    if(Objects.nonNull(suggestion.getRecordingId())) {
	    	 depDB.setRecordingId(suggestion.getRecordingId());
	    }
		return repository.save(depDB);
	}

	@Override
	public void deleteStatementChgSuggestionById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public StatementChgSuggestion findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<StatementChgSuggestion> findByRecordingId(Long recordingId) {
		
		return (List<StatementChgSuggestion>)repository.findByRecordingId(recordingId);
	}

	@Override
	public StatementChgSuggestion updateStatementChgSuggestion(StatementChgSuggestion statement, Long recordingid,
			String suggestionThreadId) {
		
	 StatementChgSuggestion obj=repository.findBySuggestionThreadId(suggestionThreadId);
		if(suggestionThreadId.equals("ed3226e4d716d31d6490fbb3f715c22d1")) {
			System.out.println("ed3226e4d716d31d6490fbb3f715c22d1 Line 89 it is null");
		}else {
			System.out.println("Line 91 it is null");
				
		}
		
		if(obj!=null && obj.getSuggestionThreadId().equalsIgnoreCase(suggestionThreadId.trim())) {
			//for(StatementChgSuggestion obj:list) {
				//if(obj.getSuggestionThreadId().equals(suggestionThreadId)) {
					//update and save then break 
					System.out.println("line 93  "+ obj.getSuggestionThreadId()+"|"+suggestionThreadId);
					return updateStatementChgSuggestion(statement,obj.getId());
					//break;
				//}
			//}
		}else {
			//create a new records in DB
			System.out.println("line 100  it is null" + suggestionThreadId);
			
			return repository.save(statement);
		}
		
	//return null;
		
		
	}

	@Override
	public String findBySuggestionThread(String threadId) {
		StatementChgSuggestion obj=repository.findBySuggestionThreadId(threadId);
		if(obj!=null) {
			return obj.getSuggestionThread();
		}
		return null;
	}


	
	

}
