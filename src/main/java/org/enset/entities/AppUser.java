package org.enset.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
@Table(name="users")
public class AppUser implements Serializable {
	@Id
	@GeneratedValue
	private Long id ;
	private String username ;
	
	private String password ;
	private String email ;
	private boolean actived ;
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles= new ArrayList<>() ;
	public AppUser(String username, String password,boolean actived, Collection<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.actived= actived ;
		this.roles = roles;
	}
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	public AppUser(String username, String password, String email, boolean actived, Collection<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.actived = actived;
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	
	

}
