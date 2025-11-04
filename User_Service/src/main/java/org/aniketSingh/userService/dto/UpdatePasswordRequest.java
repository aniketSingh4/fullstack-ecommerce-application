package org.aniketSingh.userService.dto;

import lombok.Getter;

@Getter
public class UpdatePasswordRequest 
{
	 private String currentPassword;
	 private String newPassword;
	 
	 //Getter Method
	 public String getCurrentPassword() {
		 return currentPassword;
	 }
	 public String getNewPassword() {
		 return newPassword;
	 }
	 
	 

}
