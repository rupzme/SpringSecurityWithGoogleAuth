package sample.application.dao.impl;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import sample.application.dao.UserDetailsDAO;

@Repository
public class HardCodedUserDetailsDAO implements UserDetailsDAO {

	private final HashMap<String, String[]> users;

	public HardCodedUserDetailsDAO() {

		users = new HashMap<String, String[]>();

		// ADD TEST USERS & ROLES HERE - See test for examples.
		String[] userRoles = new String[] { "ROLE_USER" };
		String[] managerRoles = new String[] { "ROLE_USER", "ROLE_MANAGER" };

		users.put("test.manager@gmail.com", managerRoles);
		users.put("test.user@gmail.com", userRoles);
	}

	public HardCodedUserDetailsDAO(HashMap<String, String[]> userData) {
		users = userData;
	}

	public UserDetails retrieveUser(String username) {

		if (!users.containsKey(username))
			return null;

		return new User(username, "", extractRoles(users.get(username)));
	}

	private HashSet<GrantedAuthority> extractRoles(String[] roles) {

		HashSet<GrantedAuthority> grantedAuthoritys = new HashSet<GrantedAuthority>();

		for (String role : roles) {
			grantedAuthoritys.add(new SimpleGrantedAuthority(role));
		}

		return grantedAuthoritys;
	}

}
