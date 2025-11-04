package org.aniketSingh.userService.service;


import java.util.Date;
import java.util.Optional;

import org.aniketSingh.userService.EmailExistException;
import org.aniketSingh.userService.dto.AddUserRequest;
import org.aniketSingh.userService.dto.RegisterUserRequest;
import org.aniketSingh.userService.dto.UpdatePasswordRequest;
import org.aniketSingh.userService.dto.UpdateUserRequest;
import org.aniketSingh.userService.exception.PasswordNotMatchException;
import org.aniketSingh.userService.model.User;
import org.aniketSingh.userService.model.UserPrincipal;
import org.aniketSingh.userService.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;
import static org.aniketSingh.userService.enumeration.Role.ROLE_USER;

@Slf4j
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private Logger log;
	
	private LoginAttemptService loginAttemptService;
	
	public User register(RegisterUserRequest user)  
	{
        validateEmail( user.getEmail());
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setJoinDate(new Date());
        newUser.setPassword(encodePassword(user.getPassword()));
        newUser.setActive(true);
        newUser.setNotLocked(true);
        newUser.setRoles(ROLE_USER.name());
        newUser.setAuthorities(ROLE_USER.getAuthorities());
        //newUser.setProfileImageUrl(getTemporaryProfileImageUrl(user.getEmail()));
        userRepo.save(newUser);
        return newUser;
    }
	
	public User addNewUser(AddUserRequest user)  
	{
        validateEmail( user.getEmail());
        User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setJoinDate(new Date());
        newUser.setPassword(encodePassword(user.getPassword()));
        newUser.setActive(user.isActive());
        newUser.setNotLocked(user.isNonLocked());
        //newUser.setRole(getRoleEnumName(user.getRole()).name());
        //newUser.setAuthorities(getRoleEnumName(user.getRole()).getAuthorities());
        //newUser.setProfileImageUrl(getTemporaryProfileImageUrl(user.getEmail()));
        userRepo.save(newUser);
        return newUser;
    }
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		User user = userRepo.findUserByEmail(username).orElseThrow(()-> new RuntimeException("No Such User Found!."));
		validateLoginAttempt(user);
		user.setLastLoginDate(user.getLastLoginDate());
        user.setLastLoginDate(new Date());
        userRepo.save(user);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        //log.info(FOUND_USER_BY_EMAIL + email);
        return userPrincipal;
	}
	
	
	//Update User Method
	public User updateUser(UpdateUserRequest user)
	{
        User currentUser = userRepo.findUserByEmail(user.getEmail()).orElseThrow(()-> new RuntimeException("No Such User Found!."));
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setProfileImageUrl(user.getProfileImageURL());
        //currentUser.setActive(user.isActive());
        // currentUser.setNotLocked(user.isNonLocked());
        // currentUser.setRole(getRoleEnumName(user.getRole()).name());
        // currentUser.setAuthorities(getRoleEnumName(user.getRole()).getAuthorities());
        // saveProfileImage(currentUser, user.getProfileImage());
        return userRepo.save(currentUser);
    }
	
	
	//Update Password Method
	public User updatePassword(UpdatePasswordRequest newUser,String token)
	{
        DecodedJWT decodedJWT =  jwtTokenProvider.decodeToken(token);
        String email = decodedJWT.getClaim("email").asString();
        User currentUser = userRepo.findUserByEmail(email);

        if(passwordEncoder.matches(newUser.getCurrentPassword(), currentUser.getPassword())){
            currentUser.setPassword(encodePassword(newUser.getNewPassword()));
        }else{
            throw new PasswordNotMatchException("The current password not correct!");
        }

        return userRepo.save(currentUser);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void validateLoginAttempt(User user) 
	{
        if(user.isNotLocked()) {
            if(loginAttemptService.hasExceededMaxAttempts(user.getEmail())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getEmail());
        }
    }
	
	private void validateEmail(String email) {
        Optional<User> userByNewEmail = userRepo.findUserByEmail(email);

        if (userByNewEmail.isPresent()) 
        {
            throw new EmailExistException("EMAIL_ALREADY_EXISTS");
        }
    }
	
	private String encodePassword(String password) 
	{
        return passwordEncoder.encode(password);
    }
	
//	private String getTemporaryProfileImageUrl(String email) 
//	{
//        return UriComponentsBuilder.fromCurrentContextPath()
//        		.path(DEFAULT_USER_IMAGE_PATH + email).toUriString();
//    }

}
