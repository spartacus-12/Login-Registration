package org.enset.service;

import java.util.List;

import org.enset.dao.RoleRepository;
import org.enset.dao.UserRepository;
import org.enset.entities.Role;
import org.enset.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestService {
	@Autowired
	private UserRepository userRepository ;
	@Autowired
	private RoleRepository roleRepository ;
	@Secured(value= {"ROLE_ADMIN"})
	@RequestMapping(value="/adduser")
	public User save(User u) {
		return userRepository.save(u);
	}
	@Secured(value= {"ROLE_ADMIN"})
	@RequestMapping(value="/findusers")
	public List<User>findAll(){
		return userRepository.findAll() ;
	}
	@Secured(value= {"ROLE_ADMIN"})
	@RequestMapping(value="/addrole")
	public Role saveRole(Role r) {
		return roleRepository.save(r);
	}
	@Secured(value= {"ROLE_ADMIN"})
	@RequestMapping(value="/findroles")
	public List<Role>findAllRoles(){
		return roleRepository.findAll() ;
	}
	@Secured(value= {"ROLE_ADMIN"})
	@RequestMapping(value="/addRoleToUser")
	public User addRoleToUser(String username,String role) {
		User u = userRepository.findById(username).get();
		Role r = roleRepository.findById(role).get();
		u.getRoles().add(r);
		userRepository.save(u);
		return u ;	
	}
}
