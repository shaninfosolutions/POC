package mmk.com.sg.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mmk.com.sg.data.entity.Statement;
import mmk.com.sg.data.repository.StatementRepository;


@Service
public class C2ISStatementService implements C2ISStatement{
	
	@Autowired
	private StatementRepository repository;

	@Override
	public Statement saveStatement(Statement statement) {
		return repository.save(statement);
	}

	@Override
	public List<Statement> fetchStatementList() {
		return (List<Statement>) repository.findAll();
	}

	@Override
	public Statement updateStatement(Statement statement, Long statementId) {
		Statement depDB = repository.findById(statementId).get();
		 
	    if (Objects.nonNull(statement.getCaseFileNo()) && !"".equalsIgnoreCase(statement.getCaseFileNo())) {
	        depDB.setCaseFileNo(statement.getCaseFileNo());
	    }
	    
	    if (Objects.nonNull(statement.getOffender()) && !"".equalsIgnoreCase(statement.getOffender())) {
	        depDB.setOffender(statement.getOffender());
	    }
	    
	    if (Objects.nonNull(statement.getPersonInCharge()) && !"".equalsIgnoreCase(statement.getPersonInCharge())) {
	        depDB.setPersonInCharge(statement.getPersonInCharge());
	    }
	    
	    if (Objects.nonNull(statement.getStage()) && !"".equalsIgnoreCase(statement.getStage())) {
	        depDB.setStage(statement.getStage());
	    }
	    
	   // if (Objects.nonNull(statement.getContent()) && !"".equalsIgnoreCase(statement.getContent())) {
	        depDB.setContent(statement.getContent());
	   // }
	    
	    if (Objects.nonNull(statement.getStatementNo()) && !"".equalsIgnoreCase(statement.getStatementNo())) {
	        depDB.setStatementNo(statement.getStatementNo());
	    }
	    
	    
	    return repository.save(depDB); 
	}

	@Override
	public void deleteStatementById(Long statementId) {
		repository.deleteById(statementId);
		
	}

	@Override
	public Statement findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public Statement findByStatementNo(String statementNo) {
		
		return repository.findByStatementNo(statementNo);
	}
	
	
	

}
