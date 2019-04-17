package org.wecancodeit.medicinecabinetapp.base.classes;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import org.springframework.data.annotation.Transient;
import org.springframework.mail.SimpleMailMessage;
import org.wecancodeit.medicinecabinetapp.password.PasswordResetToken;
import org.wecancodeit.medicinecabinetapp.repositories.PasswordTokenRepository;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String userName;
	private String firstName;
	private String lastName;
	private String userPassword;
	private String userPhone;
	private String userEmail;

	
@Transient
private String passwordConfirm;

@ManyToMany
private Set<Role> roles;
	
	public User () {
		
	}
	
	public User(String userName, String firstName, String lastName, String userPassword, String userPhone, String userEmail) {
		this.userName = userName;
		this.firstName=firstName;
		this.lastName=lastName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userEmail=userEmail;
	}
	
	public long getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName=userName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	
	public String lastName() {
		return lastName;
	}
	public void setLastname(String lastName) {
		this.lastName=lastName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(String userPassword) {
		this.userPassword=userPassword; 
	}
	
	public String getUserPhone() {
		return userPhone;
	}
	
	public void setUserPhone(String userPhone) {
		this.userPhone=userPhone;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail=userEmail;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm=passwordConfirm;
	}
	
	public Set<Role> getRoles(){
		return roles;
	}
	
	public void setRoles(HashSet<Role> roles) {
		this.roles=roles;
	}

	public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        PasswordTokenRepository.save(myToken);
    }
    
    private SimpleMailMessage constructResetTokenEmail(
    		  String contextPath, Locale locale, String token, User user) {
    		    String url = contextPath + "/user/changePassword?id=" + 
    		      user.getId() + "&token=" + token;
    		    String message = message.getMessage("message.resetPassword", 
    		      null, locale);
    		    return constructEmail("Reset Password", message + " \r\n" + url, user);
    		}
    		 
    		private SimpleMailMessage constructEmail(String subject, String body, 
    		  User user) {
    		    SimpleMailMessage email = new SimpleMailMessage();
    		    email.setSubject(subject);
    		    email.setText(body);
    		    email.setTo(user.getUserEmail());
    		    email.setFrom(env.getProperty("support.email"));
    		    return email;
    		}

	
}
