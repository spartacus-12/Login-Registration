package org.enset.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class VerificationToken implements Serializable{
    private static final int EXPIRATION = 60 * 24;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    private String token;
   
    @OneToOne(targetEntity = AppUser.class, fetch = FetchType.EAGER)
    private AppUser user;
     
    private Date expiryDate;
    
    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public AppUser getUser() {
		return user;
	}
	public void setUser(AppUser user) {
		this.user = user;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public static int getExpiration() {
		return EXPIRATION;
	}
	public VerificationToken(String token, AppUser user, Date expiryDate) {
		super();
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}
	public VerificationToken() {
		super();
		// TODO Auto-generated constructor stub
	}
    

}
