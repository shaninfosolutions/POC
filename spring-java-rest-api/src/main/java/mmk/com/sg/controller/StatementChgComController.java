package mmk.com.sg.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mmk.com.sg.data.entity.StatementChgComment;
import mmk.com.sg.service.StatementChgComService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class StatementChgComController {
	
	@Autowired
    private StatementChgComService serviceImpl;
	
	@GetMapping("comment")
	public List<StatementChgComment> findAllStatement() {
    	 return this.serviceImpl.fetchStatementChgCommentList();
	}
	
	@PostMapping("/comment")
    @ResponseStatus(value = HttpStatus.CREATED)
    public StatementChgComment saveStatement( @RequestBody StatementChgComment comment)
    {
        return this.serviceImpl.saveStatementChgComment(comment);
    }
	
	
	@GetMapping(value = "/comment/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public StatementChgComment findById(@PathVariable Long id) {
		return this.serviceImpl.findById(id);
	}
	
	//Update operation
    @PutMapping("/comment/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StatementChgComment  updateComment(@RequestBody StatementChgComment comment, @PathVariable("id") Long id)
    {
        return this.serviceImpl.updateStatementChgComment(comment, id);
    }
    
    
    
    @GetMapping(value = "/comment/recordingid/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<StatementChgComment> findByRecordingId(@PathVariable Long id) {
    	return this.serviceImpl.findByRecordingId(id);
	}
    
    @GetMapping(value = "/comment/commentchangelist/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<String> getListOfComments(@PathVariable Long id) {
    	List<StatementChgComment> list=this.serviceImpl.findByRecordingId(id);
    	if(!list.isEmpty()) {
    		List<String> field1List = list.stream().map(StatementChgComment::getCommentThread).collect(Collectors.toList());
    	return field1List;
    	}
    	else {
    		return null;
    	}
    	
    	
	}
    
    
  //Update operation
    @PutMapping("/comment/updated/{id}/{threadid}")
    @ResponseStatus(value = HttpStatus.OK)
    public StatementChgComment  updateCommentByIdandCommentID(@RequestBody StatementChgComment comment, 
    					@PathVariable("id") Long id,
    					@PathVariable("threadid") String threadid)
    {
        return this.serviceImpl.updateStatementChgCommentId(comment, threadid, comment.getCommentId());
    }
    
    @GetMapping(value = "/comment/threadid/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String findByThreadId(@PathVariable String id) {
    	return this.serviceImpl.findByThreadId(id);
	}
    

}
