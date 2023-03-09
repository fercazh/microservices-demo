package mx.com.fer.todoapi.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mx.com.fer.todoapi.domain.User;
import mx.com.fer.todoapi.repository.UserRepository;


@Service(value = "jwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userDao;
	
	private final String PREFIX_ROLE = "ROLE_";
	
	public UserDetails loadUserByUsername(String username) {
		User user = userDao.findByUsername(username);
		if(logger.isDebugEnabled()) logger.debug("### USER: " + username + " - " + (user != null && user.getRole() != null ? user.getRole().getName() : null));
		
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}else if(!user.getActive()) {
			throw new UsernameNotFoundException("Invalid user, username: " + username);
		}else {
			List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority( PREFIX_ROLE + user.getRole().getName()));        
			System.out.println("loadUserByUsername: AUTH:" + authorities.get(0).getAuthority());
			return new org.springframework.security.core.userdetails.User(
					user.getUsername()
					, user.getPassword()
					, authorities
				);
		}		
	}

}