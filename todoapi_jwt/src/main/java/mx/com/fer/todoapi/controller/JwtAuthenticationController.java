package mx.com.fer.todoapi.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.fer.todoapi.config.JwtTokenUtil;
import mx.com.fer.todoapi.dto.JwtResponse;
import mx.com.fer.todoapi.dto.LoginDto;
import mx.com.fer.todoapi.dto.UserDto;
import mx.com.fer.todoapi.service.impl.JwtUserDetailsService;

//import com.mx.fer.thd.warehouse.config.JwtTokenUtil;
//import com.mx.fer.thd.warehouse.service.JwtUserDetailsService;
//import com.mx.fer.thd.warehouse.web.dto.JwtRequest;
//import com.mx.fer.thd.warehouse.web.dto.JwtResponse;
//import com.mx.fer.thd.warehouse.web.dto.UserDto;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginDto authenticationRequest) throws Exception {
		System.out.printf("#####%s, '%s'", authenticationRequest.getUsername(), authenticationRequest.getPassword());


		final String token = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private String authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		

		try {
			final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities()));
			return  jwtTokenUtil.generateToken(userDetails);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
