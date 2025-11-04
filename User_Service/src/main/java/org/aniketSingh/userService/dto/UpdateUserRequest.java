package org.aniketSingh.userService.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest 
{
	private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String profileImageURL;
    private String role;
    private boolean isNonLocked;
    private boolean isActive;
    
    
    //Getter Method
    public String getRole() {
		return role;
	}
	public boolean isNonLocked() {
		return isNonLocked;
	}
	public boolean isActive() {
		return isActive;
	}
	public String getEmail() {
		return email;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPassword() {
		return password;
	}
	public String getProfileImageURL() {
		return profileImageURL;
	}

}
