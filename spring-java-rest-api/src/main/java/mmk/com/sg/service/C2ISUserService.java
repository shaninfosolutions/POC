package mmk.com.sg.service;

import java.util.List;

import mmk.com.sg.data.entity.C2ISUser;

public interface C2ISUserService {
	
	// save operation
	C2ISUser saveUser(C2ISUser user);
 
    // read operation
    List<C2ISUser> fetchUsertList();
 
    // update operation
    C2ISUser updateUser(C2ISUser department, Long userId);
 
    // delete operation
    void deleteUserById(Long userId);
    
    C2ISUser findById(Long id);
    
    List<C2ISUser> findByName(String name);
	
	C2ISUser findByChannel(Long channel);
	
	C2ISUser findByUserId(String userId);

}
