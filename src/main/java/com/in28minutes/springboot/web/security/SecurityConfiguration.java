package com.in28minutes.springboot.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration 													 //bcz we want spring to pick this as configuration file
public class SecurityConfiguration extends WebSecurityConfigurerAdapter  // to configure spring security and override few methods for our
																		//own security configuration
{ 
	/*we will  create 2 kinds of config 	
	 * 1). create users-> in28minutes & pass-> dummy and 
	 *  and assign role to that user
	 * 2). for unaunthenticated user comes-we can ask userid and pass-(show this is login form) */
	
	//1. create user and add inmemory authentication
	
	@Autowired
	public void ConfigureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception //AuthenticationManagerBuilder is inbuilt
	{
		auth.inMemoryAuthentication().withUser("user").password("user")
		.roles("USER","ADMIN");
	}
	
	/*now in place of getting that pop up for login, we will take user to our login page ,
	 * we do this by @override overriding a "configure(HttpSecurity http)" method under WebSecurityConfigurerAdapter
	 */
	
	@Override
	
	protected void configure(HttpSecurity http) throws Exception
	{
		/*we are saying here that for  "/login" , permit everybody but if sm1 wants to goto any /todo page or "/" root page
		 * we need to see if he has role of USER, if not then show him formlogin()       */
		http.authorizeRequests().antMatchers("/login").permitAll()			
		.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
		.formLogin(); // we will get default spring login form
	}
		
	

}
