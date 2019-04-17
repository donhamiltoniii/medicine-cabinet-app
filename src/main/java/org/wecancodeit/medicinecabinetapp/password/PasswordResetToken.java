package org.wecancodeit.medicinecabinetapp.password;


import java.util.Date;
//import java.sql.Date;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.wecancodeit.medicinecabinetapp.base.classes.User;
import org.wecancodeit.medicinecabinetapp.repositories.PasswordTokenRepository;


@Entity
public class PasswordResetToken {
  
    public PasswordResetToken(String token, User user) {
	
	}

	private static final int EXPIRATION = 60 * 24;
  
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  
    private String token;
  
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
  
    private Date expiryDate;

}

