//package io.ennate.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	// Authentication : User --> Roles
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser("ennate").password("ennate").roles("USER").and().withUser("admin_ennate")
//				.password("admin_ennate").roles("USER", "ADMIN");
//	}
//
//	// Authorization : Role -> Access
//	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().and().authorizeRequests().antMatchers("/").hasRole("USER").antMatchers("/**").hasRole("ADMIN")
//				.and().csrf().disable().headers().frameOptions().disable();
//	}
//}
