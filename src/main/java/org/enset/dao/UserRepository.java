package org.enset.dao;

import org.enset.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>{
public AppUser findByUsername(String username);
}
