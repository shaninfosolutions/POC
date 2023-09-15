package mmk.com.sg.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mmk.com.sg.data.entity.C2ISUser;
import mmk.com.sg.data.repository.C2ISUserRepository;

@Service
public class C2ISUserServiceImpl implements C2ISUserService{
	
@Autowired
private C2ISUserRepository userRepository;
   
@Override
public C2ISUser saveUser(C2ISUser user) {
	return userRepository.save(user);
}

@Override
public List<C2ISUser> fetchUsertList() {
	  return (List<C2ISUser>) userRepository.findAll();
}

@Override
public C2ISUser updateUser(C2ISUser user, Long userId) {
	C2ISUser depDB = userRepository.findById(userId).get();
	 
    if (Objects.nonNull(user.getName()) && !"".equalsIgnoreCase(user.getName())) {
        depDB.setName(user.getName());
    }
   
    if (Objects.nonNull(user.getAvatar()) && !"".equalsIgnoreCase(user.getAvatar())) {
        depDB.setAvatar(user.getAvatar());
    }
    if (Objects.nonNull(user.getRole()) && !"".equalsIgnoreCase(user.getRole())) {
        depDB.setRole(user.getRole());
    }

    if (Objects.nonNull(user.getEmail()) && !"".equalsIgnoreCase(user.getEmail())) {
        depDB.setEmail(user.getEmail());
    }

    return userRepository.save(depDB);
}

@Override
public void deleteUserById(Long userId) {
	userRepository.deleteById(userId);
	
}

@Override
public C2ISUser findById(Long id) {
	return userRepository.findById(id).get();
}

@Override
public List<C2ISUser> findByName(String name) {
	return userRepository.findByName(name);
}

@Override
public C2ISUser findByChannel(Long channel) {
	return userRepository.findByChannel(channel);
}

@Override
public C2ISUser findByUserId(String userId) {
	return userRepository.findByUserId(userId);
}




}
