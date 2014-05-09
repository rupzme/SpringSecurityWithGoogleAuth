package sample.application.web.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import sample.application.security.facade.UserFacade;

public class GoogleSecurityAuthenticationFilter extends GenericFilterBean {

	private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource = new WebAuthenticationDetailsSource();
	// TODO Pass this in / set
	private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserFacade userFacade;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		
		if (authentication == null) {
			
			UserDetails googleUser = getGoogleUserFromRequest(request);
			
			if (googleUser != null) {
				
				PreAuthenticatedAuthenticationToken token = new PreAuthenticatedAuthenticationToken(
						googleUser, null);
				token.setDetails(authenticationDetailsSource.buildDetails((HttpServletRequest) request));

				try {
					authentication = authenticationManager.authenticate(token);
					
					SecurityContextHolder.getContext().setAuthentication(
							authentication);

				} catch (AuthenticationException e) {
					//TODO Failure handler should be passed in and tested.
					failureHandler.onAuthenticationFailure(
							(HttpServletRequest) request,
							(HttpServletResponse) response, e);
					return;
				}
			} 
		}

		chain.doFilter(request, response);

	}

	private UserDetails getGoogleUserFromRequest(ServletRequest request)
			throws IOException {
	
		//TODO Needs cleaning up!!
		
		String authCode = request.getParameter("code");
		System.out.println("code: "+authCode);
		if (authCode==null) return null;
//		GoogleAuthHelper googleAuthHelper = new GoogleAuthHelper();
//		String jsonGoogleUser = googleAuthHelper.getUserInfoJson(authCode);
//		System.out.println("request: "+jsonGoogleUser);
//		
//		GoogleUserConverter googleUserConverter = new GoogleUserConverter();
//		
//		return googleUserConverter.convert(jsonGoogleUser);
		
		return userFacade.getGoogleUserDetails(authCode);
		
	}
	
	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
