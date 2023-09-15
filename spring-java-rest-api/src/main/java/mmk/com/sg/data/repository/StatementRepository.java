package mmk.com.sg.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mmk.com.sg.data.entity.Statement;

@Repository
public interface StatementRepository extends CrudRepository<Statement, Long>{
	
	Statement findByStatementNo(String statementNo);

}
