package mmk.com.sg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/")
public class UserController {
	
	@Autowired
    private UserServiceImpl userServiceImpl;
    
    @GetMapping("users")
	public List<User> findAllUser() {
    	 return this.userServiceImpl.fetchUsertList();
	}
    
    @PostMapping("/users")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User saveUser( @RequestBody User User)
    {
 
        return userServiceImpl.saveUser(User);
    }
    
    @GetMapping(value = "/users/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public User findById(@PathVariable Long id) {
		return this.userServiceImpl.findById(id);
	}
    
 // Update operation
    @PutMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public User
    updateUser(@RequestBody User user, @PathVariable("id") Long userId)
    {
 
        return userServiceImpl.updateUser(user, userId);
    }
    
    // Delete operation
    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public String deleteUserById(@PathVariable("id") Long userId)
    {
 
    	userServiceImpl.deleteUserById( userId);
        return "Deleted Successfully";
    }

}
