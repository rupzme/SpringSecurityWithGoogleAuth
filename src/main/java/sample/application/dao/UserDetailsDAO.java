package sample.application.dao;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsDAO {
	public UserDetails retrieveUser(String username);
}
