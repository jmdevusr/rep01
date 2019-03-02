package com.usuarios.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usuarios.demo.model.Status;
import com.usuarios.demo.model.User;
import com.usuarios.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	public User save(User user) {

		return repository.saveAndFlush(user);
	}
	
	
	public List<User> getAll() {

		return repository.findAll();
	} 
	
	
	public List<User> getActive() {

		return repository.find(Status.ACTIVE);
	} 
	
	
	public User getUserById(Long id) {
		User userObj = null;
		
		Optional<User> optUser = repository.findById(id);
		if(optUser.isPresent()) {
			User tmpUser = optUser.get();
			if(tmpUser.getStatus()==Status.ACTIVE){
				userObj = tmpUser;
			}else {
				//The object userObj keeps null because is supposed it was deleted
				System.out.println("This user was deleted: "+tmpUser);
			}
		}
		return userObj;
	}
	
	
	
	public User update(User user) {
		User userUpdated=null;
		Optional<User> optUser = repository.findById(user.getId());
		if(optUser.isPresent()) {
			User tmpUser = optUser.get();
			if(tmpUser.getStatus()==Status.ACTIVE){
				userUpdated = repository.saveAndFlush(user);
			}else {
				System.out.println("This user was deleted: "+tmpUser);
			}
		}
		return userUpdated;
	}
	
	
	
	public User delete(Long id) {
		User userDeleted=null;
		Optional<User> optUser = repository.findById(id);
		if(optUser.isPresent()) {
			User tmpUser = optUser.get();
			if(tmpUser.getStatus()==Status.ACTIVE){
				//modify status to INACTIVE
				tmpUser.setStatus(Status.INACTIVE);
				userDeleted = repository.saveAndFlush(tmpUser);
			}else {
				System.out.println("This user was previously deleted: "+tmpUser);
			}
		}
		return userDeleted;
		
	}
	
}
