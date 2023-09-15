package mmk.com.sg.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import mmk.com.sg.data.entity.C2ISUser;

@Repository
public interface C2ISUserRepository extends CrudRepository<C2ISUser, Long>{
	
	List<C2ISUser> findByName(String name);
	
	C2ISUser findByChannel(Long channel);

	C2ISUser findByUserId(String userId);

}
