package mmk.com.sg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import mmk.com.sg.service.C2ISUserServiceImpl;

@Configuration
public class SecurityConfig  {
	
	@Autowired
	public C2ISUserServiceImpl c2isUserServiceImpl;
	
	/*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
		
		
        
        return http.build();
    }*/
	
	@Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		
	    http.csrf(csrf -> csrf.disable())
	       // .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
	       // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> 
	          {
				try {
					auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					      .anyRequest().authenticated().and().httpBasic();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        );
	    
	   // http.authenticationProvider(authenticationProvider());

	   // http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	  }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication().withUser("javainuse").password("{noop}password").roles("USER");
	}
	
}