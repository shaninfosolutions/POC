package mmk.com.sg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mmk.com.sg.data.entity.C2ISUser;
import mmk.com.sg.data.entity.Statement;
import mmk.com.sg.service.C2ISStatementService;
import mmk.com.sg.utility.FileDownloadUtil;

import java.io.IOException;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class C2ISStatementController {
	
	@Autowired
    private C2ISStatementService serviceImpl;
	
	@Value("${statement.pdf.file.input}")
	private String statementInput;
	
	@GetMapping("statement")
	public List<Statement> findAllStatement() {
    	 return this.serviceImpl.fetchStatementList();
	}
	
	@PostMapping("/statement")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Statement saveStatement( @RequestBody Statement statement)
    {
		statement.setLastUpdatedDt(new Date());
        return this.serviceImpl.saveStatement(statement);
    }
	
	@GetMapping(value = "/statement/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Statement findById(@PathVariable Long id) {
		return this.serviceImpl.findById(id);
	}
	
	
	@GetMapping(value = "/statementno/{statementNo}")
	@ResponseStatus(value = HttpStatus.OK)
	public Statement findByStatementNo(@PathVariable String statementNo) {
		return this.serviceImpl.findByStatementNo(statementNo);
	}
	
	// Update operation
    @PutMapping("/statement/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Statement
    updateStatement(@RequestBody Statement statement, @PathVariable("id") Long statementId)
    {
        return this.serviceImpl.updateStatement(statement, statementId) ;
    }
    
 // Delete operation
    @DeleteMapping("/statement/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteStatementById(@PathVariable("id") Long statementId)
    {
    	serviceImpl.deleteStatementById( statementId);
        return "Deleted Successfully";
    }
    
    
    
    
    

}
