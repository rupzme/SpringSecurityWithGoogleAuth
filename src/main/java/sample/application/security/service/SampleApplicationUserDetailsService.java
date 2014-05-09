package sample.application.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sample.application.dao.impl.HardCodedUserDetailsDAO;

@Service
public class SampleApplicationUserDetailsService implements UserDetailsService{

	@Autowired
	HardCodedUserDetailsDAO userDetailsDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		System.out.println("Hello from user details service. Looking for u: "+username);
		
		return userDetailsDAO.retrieveUser(username);
	}

}
