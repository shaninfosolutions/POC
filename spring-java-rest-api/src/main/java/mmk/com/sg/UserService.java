package mmk.com.sg;

import java.util.List;

public interface UserService {
	// save operation
    User saveUser(User user);
 
    // read operation
    List<User> fetchUsertList();
 
    // update operation
    User updateUser(User department, Long userId);
 
    // delete operation
    void deleteUserById(Long userId);
    
    User findById(Long id);
}
