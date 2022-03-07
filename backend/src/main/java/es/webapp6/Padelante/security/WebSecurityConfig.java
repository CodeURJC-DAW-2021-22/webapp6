package es.webapp6.Padelante.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        //http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/register").permitAll();
        http.authorizeRequests().antMatchers("/tourns/{id}").permitAll();
        http.authorizeRequests().antMatchers("/tourns/{id}/image").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/create_tournament").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers("/user_profile").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers("/user/{id}/image").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers("/match/{id}").hasAnyRole("USER","ADMIN");
        http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/removeUser/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/removeTournament/{id}").hasAnyRole("ADMIN");
 
     

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/loginerror");

        // Logout
         http.logout().logoutUrl("/logout");
         http.logout().logoutSuccessUrl("/");
    }
}
