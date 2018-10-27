package org.enset;



import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
public void globalConfig(AuthenticationManagerBuilder auth,DataSource dataSource) throws Exception {
		/*
		auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN","PROF");
		auth.inMemoryAuthentication().withUser("prof").password("{noop}123").roles("PROF");
		auth.inMemoryAuthentication().withUser("etd").password("{noop}123").roles("ETUDIANT");

		*/
		
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getPasswordEncoder())
		.usersByUsernameQuery("select username as principal,password as credentials,true from users where username = ?")
		.authoritiesByUsernameQuery("select user_username as principal,roles_role as role from users_roles where user_username = ?")
		.rolePrefix("ROLE_");
	
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/***","/js/***","/images/***").permitAll().anyRequest().authenticated().and()
		.formLogin().loginPage("/login").defaultSuccessUrl("/index.html").failureUrl("/login")
		.permitAll()
		.and().logout()
		.invalidateHttpSession(true).logoutUrl("/logout")
		.permitAll();

http.csrf().disable();
	//.failureUrl("/error.html");
	}
	  private PasswordEncoder getPasswordEncoder() {
	        return new PasswordEncoder() {
	            @Override
	            public String encode(CharSequence charSequence) {
	                return charSequence.toString();
	            }

	            @Override
	            public boolean matches(CharSequence charSequence, String s) {
	                return true;
	            }
	        };
	    }
	
}
