package mx.com.fer.todoapi.config;

import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig //extends WebSecurityConfigurerAdapter 
{
	private final static String[] WHITELIST = {	
			"/authenticate",
			"/register",
			"/v3/**",
			"/v2/api-docs**",
			"/v3/api-docs**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/swagger-ui**",
			"/webjars/**",
			"/actuator/info",
			
			 "/v2/api-docs",
		        "/v3/api-docs",
		        "/**/v3/api-docs",
		        "/swagger-resources",
		        "/swagger-resources/**",
		        "/configuration/ui",
		        "/configuration/security",
		        "/swagger-ui.html",
		        "**/swagger-ui.html",
		        "/**/swagger-ui.html**",
		        "/swagger-ui.html**",
		        "/webjars/**"
	};
	@Lazy
	@Autowired
	private UserDetailsService jwtUserDetailsService; //
	@Autowired 
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Value("${security.web.debug}")
	private boolean securityDebug;




	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// configure AuthenticationManager so that it knows from where to load
		// user for matching credentials
		// Use BCryptPasswordEncoder
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean("bCryptoEncoder")
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(BCryptVersion.$2A,10);
	}

//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
	
	 @Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
	    return authConfiguration.getAuthenticationManager();
	  }

//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
	 @Bean
	 public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
	  	// We don't need CSRF for this example
		httpSecurity.csrf().disable()
				// dont authenticate this particular request
		//.authorizeRequests().antMatchers("/authenticate","/authenticate**").permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
			.authorizeRequests()
            //.antMatchers("/todo", "/todo**").hasAnyAuthority("ROLE_ADMIN", "ROLE_MANAGER", "ROLE_USER") //IPORTANT pay attention between diference of hasAnyAuthority and HasAnyRole
            .antMatchers("/todo", "/todo**").hasAnyRole("USER","ADMIN","MANAGER")
            .antMatchers("/", "/**").hasAnyRole("ADMIN")
			//.antMatchers("/authenticate").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			// all other requests need to be authenticate
			.anyRequest().authenticated()
				// make sure we use stateless session; session won't be used to
				// store user's state.
			.and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

		// Add a filter to validate the tokens with every request
				.and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	 
	 
	

//    @Override
//    public void configure(WebSecurity web) throws Exception {

	  @Bean
	  public WebSecurityCustomizer webSecurityCustomizer() {
		  return (web) -> web.debug(securityDebug).ignoring().antMatchers(
				   
				  WHITELIST )
        .antMatchers(HttpMethod.OPTIONS, "/**");
    }
	  
	  
}
