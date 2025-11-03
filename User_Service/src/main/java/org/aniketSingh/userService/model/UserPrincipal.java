package org.aniketSingh.userService.model;


import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails 
{ 
	@Autowired
    private User user;

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return null;
	}

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    public UUID getUserId(){
        return user.getId();
    }

    public String getRole(){
        return user.getRoles();
    }

    public String getFirstName(){
        return user.getFirstName();
    }

    public String getLastName(){
        return user.getLastName();
    }

    public String getEmail(){
        return user.getEmail();
    }

    public String getProfileImageUrl(){
        return user.getProfileImageUrl();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isActive();
    }

	public UserPrincipal(User user) {
		super();
		this.user = user;
	}
    
    

}
