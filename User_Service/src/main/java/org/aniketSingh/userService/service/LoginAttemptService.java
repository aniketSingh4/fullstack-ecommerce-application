package org.aniketSingh.userService.service;

import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import static java.util.concurrent.TimeUnit.MINUTES;

public class LoginAttemptService 
{
	private static final int max_no_of_attempts = 5;
	private static final int attemp_increment = 1;
	LoadingCache<String, Integer> loginAttemptCache;
	
	//Default Constructor
	public LoginAttemptService()
	{
		super();
		loginAttemptCache = CacheBuilder.newBuilder().expireAfterWrite(15, MINUTES)
                .maximumSize(100).build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
		
	}
	
	public void evictUserFromLoginAttemptCache(String email) 
	{
        loginAttemptCache.invalidate(email);
    }

    public void addUserToLoginAttemptCache(String email) 
    {
        int attempts = 0;
        try {
            attempts = attemp_increment + loginAttemptCache.get(email);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        loginAttemptCache.put(email, attempts);
    }

    public boolean hasExceededMaxAttempts(String email) 
    {
        try {
            return loginAttemptCache.get(email) >= max_no_of_attempts;
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

}
