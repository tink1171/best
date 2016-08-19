package com.kp.configuration;

//import com.kp.service.SimpleSocialUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;


/**
 * Created by diman on 02.08.16.
 */
@Configuration
@ComponentScan(basePackages =  "com.kp" )
// @ImportResource({ "classpath:webSecurityConfig.xml" })
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler;


	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				//Configures form login
				.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/login")
				.failureUrl("/login?error=bad_credentials")
				//Configures the logout function
				.and()
				.logout()
				.deleteCookies("JSESSIONID")
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				//Configures url based authorization
				.and()
				.authorizeRequests()
				//Anyone can access the urls
				.antMatchers(
						"/**",
						"/login/**",
						"/user",
						"/register/**",
						"/user/authenticate"
				).permitAll();
				//The rest of the our application is protected.
				//Adds the SocialAuthenticationFilter to Spring Security's filter chain.
	}

//	@Bean
//	public SocialUserDetailsService socialUserDetailsService() {
//		return new SimpleSocialUserDetailsService(userDetailsService());
//	}

//	@Bean
//	public DaoAuthenticationProvider authProvider() {
//		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
////		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
						.userDetailsService(userDetailsService)
						.passwordEncoder(new BCryptPasswordEncoder(10));
	}
}
