package mmk.com.sg.controller;

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

import mmk.com.sg.data.entity.C2ISUser;
import mmk.com.sg.dto.model.UserDTO;
import mmk.com.sg.service.C2ISUserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1")
public class C2ISUserController {
	

	@Autowired
    private C2ISUserServiceImpl userServiceImpl;
    
    @GetMapping("users")
	public List<C2ISUser> findAllUser() {
    	 return this.userServiceImpl.fetchUsertList();
	}
    
    @PostMapping("/users")
    @ResponseStatus(value = HttpStatus.CREATED)
    public C2ISUser saveUser( @RequestBody C2ISUser User)
    {
 
        return userServiceImpl.saveUser(User);
    }
    
    @GetMapping(value = "/users/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public C2ISUser findById(@PathVariable Long id) {
		return this.userServiceImpl.findById(id);
	}
    
    @GetMapping(value = "/users/{userId}")
	@ResponseStatus(value = HttpStatus.OK)
	public C2ISUser findByUserId(@PathVariable String userId) {
		return this.userServiceImpl.findByUserId(userId);
	}
    
    
    
 // Update operation
    @PutMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public C2ISUser
    updateUser(@RequestBody C2ISUser user, @PathVariable("id") Long userId)
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
    
    @GetMapping(produces = "application/json")
	@RequestMapping({ "/users/validateLogin" })
	public UserDTO validateLogin() {
    	C2ISUser user=userServiceImpl.findByUserId("e1");
    	//public UserDTO(String status, String userId, String name, String avator, String role) {
		return new UserDTO("User successfully authenticated",user.getUserId(),user.getName(),user.getAvatar(),user.getRole(),
				user.getChannel());
	}


}
