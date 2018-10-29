package org.enset.service;

import java.util.List;

import org.enset.dao.RoleRepository;
import org.enset.dao.UserRepository;
import org.enset.entities.Role;
import org.enset.entities.AppUser;
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
	@RequestMapping(value="/adduser")
	public AppUser save(AppUser u) {
		return userRepository.save(u);
	}
	@RequestMapping(value="/findusers")
	public List<AppUser>findAll(){
		return userRepository.findAll() ;
	}
	@RequestMapping(value="/addrole")
	public Role saveRole(Role r) {
		return roleRepository.save(r);
	}
	@RequestMapping(value="/findroles")
	public List<Role>findAllRoles(){
		return roleRepository.findAll() ;
	}
	@RequestMapping(value="/addRoleToUser")
	public AppUser addRoleToUser(String username,String rolename) {
		AppUser u = userRepository.findByUsername(username);
		Role r = roleRepository.findByRolename(rolename);
		u.getRoles().add(r);
		userRepository.save(u);
		return u ;	
	}
}
