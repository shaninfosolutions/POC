package mmk.com.sg;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserServiceImpl implements UserService {
	   @Autowired
	    private UserRepository userRepository;
	   
	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> fetchUsertList() {
		  return (List<User>) userRepository.findAll();
	}

	@Override
	public User updateUser(User user, Long userId) {
		User depDB = userRepository.findById(userId).get();
		 
        if (Objects.nonNull(user.getFirstName()) && !"".equalsIgnoreCase(user.getFirstName())) {
            depDB.setFirstName(user.getFirstName());
        }
 
        if (Objects.nonNull(user.getLastName()) && !"".equalsIgnoreCase(user.getLastName())) {
            depDB.setLastName(user.getLastName());
        }
 
        if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
            depDB.setEmail(user.getEmail());
        }
        
        if (Objects.nonNull(user.getContent()) && !"".equalsIgnoreCase(user.getContent())) {
        	depDB.setContent(user.getContent());
        }
 
        return userRepository.save(depDB);
	}

	@Override
	public void deleteUserById(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	

}
