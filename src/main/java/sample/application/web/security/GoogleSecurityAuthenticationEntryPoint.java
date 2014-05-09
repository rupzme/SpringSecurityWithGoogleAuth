package sample.application.web.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import sample.application.security.service.GoogleUserAuthenticationService;

public class GoogleSecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Autowired
	private GoogleUserAuthenticationService googleUserAuthenticationService;
	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		response.sendRedirect(googleUserAuthenticationService.getAuthenticationURL());
		
	}

}
