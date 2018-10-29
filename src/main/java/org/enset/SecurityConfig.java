package org.enset;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsService userDetailsService ;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	//@Override
//public void globalConfig(AuthenticationManagerBuilder auth) throws Exception {
		/*
		auth.inMemoryAuthentication().withUser("admin").password("{noop}123").roles("ADMIN","PROF");
		auth.inMemoryAuthentication().withUser("prof").password("{noop}123").roles("PROF");
		auth.inMemoryAuthentication().withUser("etd").password("{noop}123").roles("ETUDIANT");

		*/
		//passwordEncoder(bCryptPasswordEncoder);
		//.usersByUsernameQuery("select username as principal,password as credentials,true from users where username = ?")
		//.authoritiesByUsernameQuery("select user_username as principal,roles_role as role from users_roles where user_username = ?")
		//.rolePrefix("ROLE_");
	
//	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.formLogin();
		http.authorizeRequests().antMatchers("/login*","/register/**").permitAll();
	    http.authorizeRequests().antMatchers(HttpMethod.POST,"/task/**").hasAuthority("ADMIN");
	    http.authorizeRequests().anyRequest().authenticated();
	    http.addFilter(new JWTAuthenticationFilter(authenticationManager()))
	    .addFilterBefore(new JWTAuthorizationFilter(),
	    		UsernamePasswordAuthenticationFilter.class);
	    
	 
	    

	//.failureUrl("/error.html");
	}
	//  private PasswordEncoder getPasswordEncoder() {
	  //      return new PasswordEncoder() {
	    //        @Override
	      //      public String encode(CharSequence charSequence) {
	        //        return charSequence.toString();
	          //  }

	           // @Override
	           // public boolean matches(CharSequence charSequence, String s) {
	            //    return true;
	            //}
	      //  };
	    //}
	
}
