package com.appdeveloperblog.photoapp.api.users.data;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.repository.CrudRepository;

import com.appdeveloperblog.photoapp.api.users.shared.UserDto;

@Entity
@Table(name ="users")
public class UserEntity implements  Serializable {

	private static final long serialVersionUID = 9086601618107379054L;

	@javax.persistence.Id
	@GeneratedValue
	private long Id;
	
	@Column(nullable = false,length = 50)
	private String firstName;

	@Column(nullable = false,length = 50)
	private String lastName;

	@Column(nullable = false,length = 120, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String userId;

	@Column(nullable = false, unique = true)
	private String encryptedPassword;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
}
