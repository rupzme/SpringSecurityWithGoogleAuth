package sample.application.web.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class GoogleSecurityAuthenticationProvider implements
		AuthenticationProvider {

	UserDetailsService sampleAppplicationUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		String username = (String) authentication.getName();
	
		UserDetails userDetails = sampleAppplicationUserDetailsService.loadUserByUsername(username);
		
		if (userDetails==null) throw new BadCredentialsException("User not recognised.");
		
		return new PreAuthenticatedAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setSampleApplicationUserDetailsService(
			UserDetailsService sampleAppplicationUserDetailsService) {
		this.sampleAppplicationUserDetailsService = sampleAppplicationUserDetailsService;
	}
}
