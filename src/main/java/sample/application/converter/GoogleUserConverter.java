package sample.application.converter;

import java.util.HashSet;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import sample.application.security.entity.GoogleUser;

public class GoogleUserConverter {

	public UserDetails convert(String jsonGoogleUser) {
		GoogleUser googleUser = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			googleUser = mapper.readValue(jsonGoogleUser, GoogleUser.class);
		} catch (Exception e) {
			// TODO Log exception
			e.printStackTrace();
		} 
		
		return new User(googleUser.getEmail(),"", new HashSet<GrantedAuthority>());
	}

}
