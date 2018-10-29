package org.enset.service;

import org.enset.entities.Role;
import org.enset.entities.AppUser;

public interface AccountService {
public AppUser saveUser(AppUser user);
public Role saveRole(Role role);
public void addRoleToUser(String username,String roleName);
public AppUser findUserByUsername(String username);
}
