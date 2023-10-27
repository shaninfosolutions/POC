package mmk.com.sg.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mmk.com.sg.data.entity.StatementChgComment;
import mmk.com.sg.data.entity.StatementChgSuggestion;
import mmk.com.sg.data.repository.StatementChgComRepository;

@Service
public class StatementChgComServiceImpl implements StatementChgComService{

	@Autowired
	private StatementChgComRepository repository;
	
	@Override
	public StatementChgComment saveStatementChgComment(StatementChgComment comment) {
	
		return repository.save(comment);
	}

	@Override
	public List<StatementChgComment> fetchStatementChgCommentList() {
		
		return (List<StatementChgComment> )repository.findAll();
	}

	@Override
	public StatementChgComment updateStatementChgComment(StatementChgComment comment, Long id) {
		StatementChgComment depDB = repository.findById(id).get();
		 
	    if (Objects.nonNull(comment.getCommentThread()) && !"".equalsIgnoreCase(comment.getCommentThread())) {
	        depDB.setCommentThread(comment.getCommentThread());
	    }
	    
	    if(Objects.nonNull(comment.getRecordingId())) {
	    	 depDB.setRecordingId(comment.getRecordingId());
	    }
	    
	    if(Objects.nonNull(comment.getCommentThreadId())) {
	    	depDB.setCommentThreadId(comment.getCommentThreadId());
	    }
	    
	    if (Objects.nonNull(comment.getResolvedBy()) && !"".equalsIgnoreCase(comment.getResolvedBy())) {
	        depDB.setResolvedBy(comment.getResolvedBy());
	    }
	    
	    if (Objects.nonNull(comment.getResolvedAt()) ) {
	        depDB.setResolvedAt(comment.getResolvedAt());
	    }
	    
		return repository.save(depDB);
	}

	@Override
	public void deleteStatementChgCommentById(Long id) {
		repository.deleteById(id);
		
	}

	@Override
	public StatementChgComment findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public List<StatementChgComment> findByRecordingId(Long recordingId) {
		// TODO Auto-generated method stub
		return (List<StatementChgComment>)repository.findByRecordingId(recordingId);
	}

	@Override
	public void updateStatementChgComment(StatementChgComment comment, Long recordingId, String commentThreadId) {
		List<StatementChgComment> list=repository.findByRecordingId(recordingId);
		if(list!=null && !list.isEmpty()) {
			for(StatementChgComment obj:list) {
				if(obj.getCommentThreadId().equals(commentThreadId)) {
					//update and save then break 
					updateStatementChgComment(comment,obj.getId());
					break;
				}
			}
		}else {
			//create a new records in DB
			repository.save(comment);
		}
		
		
	}

	@Override
	public StatementChgComment updateStatementChgCommentId(StatementChgComment comment, String threadId, String commentId) {
		StatementChgComment obj=repository.findByCommentThreadId(commentId);
		StatementChgComment obj1=repository.findByCommentThreadIdAndCommentId(threadId,commentId);
		
		if(obj!=null && obj.getCommentId().equalsIgnoreCase(commentId.trim())) {
			//for(StatementChgComment obj:list) {
				//if(obj.getCommentId()!=null&& obj.getCommentId().equals(commentId)) {
					return updateStatementChgComment(comment,obj.getId());
					
				//}
			//}
		}
		else if(obj1!=null && obj1.getCommentThreadId().equalsIgnoreCase(threadId.trim())
				) {
			return updateStatementChgComment(comment,obj1.getId());
		}
		else {
			return repository.save(comment);
		}
		
		//return null;
		
	}

	@Override
	public String findByThreadId(String id) {
		
		StatementChgComment obj=repository.findByCommentThreadId(id);
		
		if(obj!=null) {
			return obj.getCommentThread();
		}
		return null;
	}

	/*@Override
	public void updateStatementChgComment(StatementChgComment comment, String threadId, String commentId) {
		if(commentId==null && commentId.isBlank()) {
			List<StatementChgComment> depDB = repository.findByCommentThreadId(threadId);
			
			if(depDB==null && depDB.isEmpty()) {
				repository.save(comment);
			}
		}else {
			StatementChgComment depDB = repository.findByCommentThreadIdAndCommentId(threadId, commentId);
			if(depDB!=null) {
				
			}
		}
		
	}*/

}
