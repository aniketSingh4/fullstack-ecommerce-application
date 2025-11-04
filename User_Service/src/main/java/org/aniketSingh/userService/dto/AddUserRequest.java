package org.aniketSingh.userService.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddUserRequest 
{
	 @NotNull
	 private String email;
	 
	 @NotNull
	 private String firstName;
	 @NotNull
	 private String lastName;
	 @NotNull
	 private String password;
	 private String role;
	 private boolean isNonLocked;
	 private boolean isActive;
	 //private MultipartFile profileImage;
	 
	//Getter Method
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
	 public String getRole() {
		 return role;
	 }
	 public boolean isNonLocked() {
		 return isNonLocked;
	 }
	 public boolean isActive() {
		 return isActive;
	 }
	 

}
