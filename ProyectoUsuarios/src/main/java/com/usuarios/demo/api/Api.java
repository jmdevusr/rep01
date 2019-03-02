package com.usuarios.demo.api;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.demo.model.Status;
import com.usuarios.demo.model.User;
import com.usuarios.demo.model.UserResponseToQuery;
import com.usuarios.demo.service.UserService;
import com.usuarios.demo.util.Util;


@RestController
public class Api {

	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/demo", method=RequestMethod.GET)
	public User getById() {
		return new User(1L, "Juan", "Soto", Util.dt("22-11-1955"), Status.ACTIVE);
		
	}
		
		
		
		
	@RequestMapping(value="/newUser", method=RequestMethod.POST)
	public UserResponse updateOrSave(@RequestBody @Valid UserNewRequest rq){
		
		User userToSave = new User(0L, rq.getFirstName(),rq.getLastName(), Util.dt(rq.getDateOfBirth()), Status.ACTIVE);
		
		User savedUser = userService.save(userToSave);
		
		return new UserResponse(
				savedUser.getId(),
				savedUser.getFirstName(),
				savedUser.getLastName(),
				savedUser.getDateOfBirth(),
				String.valueOf(savedUser.getStatus())
				);
	}
		
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public UserResponseToQuery updateUser(@RequestBody @Valid UserUpdateRequest rq) {
		
		User userToSave = new User(rq.getId(), rq.getFirstName(),rq.getLastName(), Util.dt(rq.getDateOfBirth()), Status.ACTIVE);
		
		User savedUser = userService.update(userToSave);
		
		UserResponse rsUser = null;
		String mensaje = "";
		if(savedUser!=null) {
			rsUser = new UserResponse(
					savedUser.getId(),
					savedUser.getFirstName(),
					savedUser.getLastName(),
					savedUser.getDateOfBirth(),
					String.valueOf(savedUser.getStatus())
					);
			mensaje = "User modified!!";
		}else {
			mensaje = "User NOT FOUND!!";
		}
		
		return new UserResponseToQuery(mensaje,rsUser);

	}
	
	
	
//	
//	@RequestMapping(value="/deleteUser", method=RequestMethod.POST)
//	public UserResponse delete(@RequestBody @Valid UserNewRequest rq){
//		
//		User userToSave = new User(2L, "patito","feo", Util.dt("01-02-1985"), Status.INACTIVE);
//		
//		User savedUser = userService.save(userToSave);
//		
//		return new UserResponse(
//				savedUser.getId(),
//				savedUser.getFirstName(),
//				savedUser.getLastName(),
//				savedUser.getDateOfBirth(),
//				String.valueOf(savedUser.getStatus())
//				);
//	}
	@RequestMapping(value="/deleteUser", method=RequestMethod.DELETE)
	public UserResponseToQuery deleteUser(@RequestParam("id") long idRq) {
		
		User userDeleted = userService.delete(idRq);
		
		
		UserResponse rsUser = null;
		String mensaje = "";
		if(userDeleted!=null) {
			rsUser = new UserResponse(
					userDeleted.getId(),
					userDeleted.getFirstName(),
					userDeleted.getLastName(),
					userDeleted.getDateOfBirth(),
					String.valueOf(userDeleted.getStatus())
					);
			mensaje = "User deleted!!";
		}else {
			mensaje = "User NOT FOUND!!";
		}
		
		return new UserResponseToQuery(mensaje,rsUser);
	}
	
	
	@RequestMapping(value="/getAllRecords", method=RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> lstAll = userService.getAll();
		return lstAll;
	}
	
	
	
	@RequestMapping(value="/getAllUsers", method=RequestMethod.GET)
	public List<User> getActiveUsers() {
		List<User> lstAll = userService.getActive();
		return lstAll;
	}
	
	
	
	@RequestMapping(value="/getUserById", method=RequestMethod.GET)
	public UserResponseToQuery getUserById(@RequestParam("id") long idRq) {
		
		UserResponse rsUser = null;
		String mensaje = "";
		User user = userService.getUserById(idRq);
		if(user!=null) {
			rsUser = new UserResponse(
					user.getId(),
					user.getFirstName(),
					user.getLastName(),
					user.getDateOfBirth(),
					String.valueOf(user.getStatus())
					);
			mensaje = "User retrieved!!";
		}else {
			mensaje = "User NOT FOUND!!";
		}
		
		return new UserResponseToQuery(mensaje,rsUser);
	}
	
	
	

	
	
}
