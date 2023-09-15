package mmk.com.sg.service;

import java.util.List;

import mmk.com.sg.data.entity.Statement;

public interface C2ISStatement {
	
		// save operation
		Statement saveStatement(Statement statement);
	 
	    // read operation
	    List<Statement> fetchStatementList();
	 
	    // update operation
	    Statement updateStatement(Statement statement, Long statementId);
	 
	    // delete operation
	    void deleteStatementById(Long statementId);
	    
	    Statement findById(Long id);
	    
	    Statement findByStatementNo(String statementNo);

}
