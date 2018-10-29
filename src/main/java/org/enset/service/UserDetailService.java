package org.enset.service;

import java.util.ArrayList;
import java.util.Collection;

import org.enset.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailService implements UserDetailsService {
@Autowired
	private AccountService accountService ;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	AppUser u= accountService.findUserByUsername(username);
	if(u==null) throw new UsernameNotFoundException(username);
	Collection<GrantedAuthority>authorities = new ArrayList<>();
	u.getRoles().forEach(r->{
		authorities.add(new SimpleGrantedAuthority(r.getRolename()));
	});
		return  new org.springframework.security.core.userdetails.User(u.getUsername(),u.getPassword(),authorities);
	}

}
