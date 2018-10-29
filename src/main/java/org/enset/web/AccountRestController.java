package org.enset.web;

import org.enset.entities.AppUser;
import org.enset.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountRestController {
	@Autowired
private AccountService accountService ;
	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm userForm) {
		if(!userForm.getPassword().equals(userForm.getRepassword()))
				throw new RuntimeException("You must confirm your password");
		AppUser user=accountService.findUserByUsername(userForm.getUsername());
		if(user!=null)throw new RuntimeException("This user already exists");
		AppUser appuser=new AppUser();
		appuser.setUsername(userForm.getUsername());
		appuser.setPassword(userForm.getPassword());
		appuser.setEmail(userForm.getEmail());
		accountService.saveUser(appuser);
		accountService.addRoleToUser(userForm.getUsername(), "ROLE_USER");
		return appuser ;
		}

}