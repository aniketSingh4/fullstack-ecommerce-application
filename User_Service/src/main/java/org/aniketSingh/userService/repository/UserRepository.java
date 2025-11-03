package org.aniketSingh.userService.repository;

import java.util.Optional;
import java.util.UUID;

import org.aniketSingh.userService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, UUID> 
{
	 Optional<User> findUserByEmail(String email);

}
