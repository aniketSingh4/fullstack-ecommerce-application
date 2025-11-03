package org.aniketSingh.userService.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Date lastLoginDate;
	private Date LastLoginDateDispaly;
	private Date JoinDate;
	private String roles;
	private String[] authorities;
	private boolean isActive;
	private boolean isLocked;
	private boolean isNotLocked;
	private String profileImageUrl;
	
	//Default and Parameterized Constructor
	
	public User(UUID id, String firstName, String lastName, String password, String email, Date lastLoginDate,
			Date lastLoginDateDispaly, Date joinDate, String roles, String[] authorities, boolean isActive,
			boolean isLocked, String profileImageUrl, boolean isNotLocked) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.lastLoginDate = lastLoginDate;
		LastLoginDateDispaly = lastLoginDateDispaly;
		JoinDate = joinDate;
		this.roles = roles;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isLocked = isLocked;
		this.profileImageUrl = profileImageUrl;
		this.isNotLocked = isNotLocked;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}




	//Getter and Setter Methods
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public Date getLastLoginDateDispaly() {
		return LastLoginDateDispaly;
	}
	public void setLastLoginDateDispaly(Date lastLoginDateDispaly) {
		LastLoginDateDispaly = lastLoginDateDispaly;
	}
	public Date getJoinDate() {
		return JoinDate;
	}
	public void setJoinDate(Date joinDate) {
		JoinDate = joinDate;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isLocked() {
		return isLocked;
	}
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public boolean isNotLocked() {
		return isNotLocked;
	}

	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}
}
