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

import mmk.com.sg.data.entity.StatementChgSuggestion;
import mmk.com.sg.service.StatementChgSugServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class StatementChgSugController {
	
	@Autowired
    private StatementChgSugServiceImpl serviceImpl;
	
	@GetMapping("trackchange")
	public List<StatementChgSuggestion> findAllStatement() {
    	 return this.serviceImpl.fetchStatementChgSuggestionList();
	}
	
	@PostMapping("/trackchange")
    @ResponseStatus(value = HttpStatus.CREATED)
    public StatementChgSuggestion saveStatement( @RequestBody StatementChgSuggestion suggestion)
    {
        return this.serviceImpl.saveStatementChgSuggestion(suggestion);
    }
	
	
	@GetMapping(value = "/trackchange/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public StatementChgSuggestion findById(@PathVariable Long id) {
		return this.serviceImpl.findById(id);
	}
	
	//Update operation
    @PutMapping("/trackchange/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public StatementChgSuggestion  updateStatement(@RequestBody StatementChgSuggestion suggestion, @PathVariable("id") Long id)
    {
        return this.serviceImpl.updateStatementChgSuggestion(suggestion, id);
    }
    
    
  //Update operation
    @PutMapping("/trackchange/threadid/{threadid}")
    @ResponseStatus(value = HttpStatus.OK)
    public StatementChgSuggestion  updateStatement(@RequestBody StatementChgSuggestion suggestion, @PathVariable("threadid")String threadid)
    {
        return this.serviceImpl.updateChgSuggestionByThreadId(suggestion, threadid);
    }
    
    
    
    
  //Update operation
    @PutMapping("/trackchange/updated/{id}/{threadid}")
    @ResponseStatus(value = HttpStatus.OK)
    public void  updateAllTrackChange(@RequestBody StatementChgSuggestion suggestion, 
    									@PathVariable("id") Long recordingId,@PathVariable("threadid") String threadId)
    {
         this.serviceImpl.updateStatementChgSuggestion(suggestion, recordingId, threadId);
    }
    
    @GetMapping(value = "/trackchange/recordingid/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<StatementChgSuggestion> findByRecordingId(@PathVariable Long id) {
    	return this.serviceImpl.findByRecordingId(id);
	}
    
    @GetMapping(value = "/trackchange/trackchangelist/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public List<String> getListOfTrackChange(@PathVariable Long id) {
    	List<StatementChgSuggestion> list=this.serviceImpl.findByRecordingId(id);
    	if(!list.isEmpty()) {
    		
    		List<String> field1List = list.stream().map(StatementChgSuggestion::getSuggestionThread).collect(Collectors.toList());
    	return field1List;
    	}
    	else {
    		return null;
    	}
    	
    	
	}
    
    @GetMapping(value = "/trackchange/threadid/{threadId}")
	@ResponseStatus(value = HttpStatus.OK)
	public String getTrackChangeByThreadId(@PathVariable String threadId) {
    	
    		return this.serviceImpl.findBySuggestionThread(threadId);

	}
	

}
