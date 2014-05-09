package sample.application.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import sample.application.security.service.SampleApplicationUserDetailsService;
import sample.application.web.security.GoogleSecurityAuthenticationEntryPoint;
import sample.application.web.security.GoogleSecurityAuthenticationFilter;
import sample.application.web.security.GoogleSecurityAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {

		auth.authenticationProvider(googleSecurityAuthenticationProvider());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.exceptionHandling().authenticationEntryPoint(
				googleSecurityEntryPoint());

		http.exceptionHandling().accessDeniedPage("/accessDenied");

		http.addFilterBefore(googleSecurityAuthenticationFilter(),
				AbstractPreAuthenticatedProcessingFilter.class);

		http.authorizeRequests().antMatchers("/user/**")
				.access("hasRole('ROLE_USER')");
		http.authorizeRequests().antMatchers("/manager/**")
				.access("hasRole('ROLE_MANAGER')");
		http.authorizeRequests().antMatchers("/*").permitAll();

	}

	@Bean
	public AuthenticationEntryPoint googleSecurityEntryPoint() {
		GoogleSecurityAuthenticationEntryPoint googleSecurityAuthenticationEntryPoint = new GoogleSecurityAuthenticationEntryPoint();

		return googleSecurityAuthenticationEntryPoint;
	}

	@Bean(name = "sampleApplicationAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationProvider googleSecurityAuthenticationProvider()
			throws Exception {
		GoogleSecurityAuthenticationProvider googleSecurityAuthenticationProvider = new GoogleSecurityAuthenticationProvider();
		googleSecurityAuthenticationProvider
				.setSampleApplicationUserDetailsService(sampleApplicationUserDetailsService());
		return googleSecurityAuthenticationProvider;
	}

	@Bean
	public UserDetailsService sampleApplicationUserDetailsService() {
		SampleApplicationUserDetailsService sampleApplicationUserDetailsService = new SampleApplicationUserDetailsService();
		return sampleApplicationUserDetailsService;
	}

	@Bean
	public GoogleSecurityAuthenticationFilter googleSecurityAuthenticationFilter()
			throws Exception {
		GoogleSecurityAuthenticationFilter googleSecurityAuthenticationFilter = new GoogleSecurityAuthenticationFilter();
		googleSecurityAuthenticationFilter
				.setAuthenticationManager(authenticationManagerBean());
		return googleSecurityAuthenticationFilter;
	}

}