package com.mailorderpharmacy.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.mailorderpharmacy.authservice.dao.UserDAO;
import com.mailorderpharmacy.authservice.entity.AuthResponse;
import com.mailorderpharmacy.authservice.entity.UserData;
import com.mailorderpharmacy.authservice.service.CustomerDetailsService;
import com.mailorderpharmacy.authservice.service.JwtUtil;

@CrossOrigin
@RestController
public class AuthController {
	
	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private CustomerDetailsService custdetailservice;
	@Autowired
	private UserDAO userservice;
	
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserData userlogincredentials) {
		final UserDetails userdetails = custdetailservice.loadUserByUsername(userlogincredentials.getUserid());
		String uid = "";
		String generateToken = "";
		if (userdetails.getPassword().equals(userlogincredentials.getUpassword())) {
			uid = userlogincredentials.getUserid();
			generateToken = jwtutil.generateToken(userdetails);
			
			return new ResponseEntity<>(new UserData(uid, null, null, generateToken), HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Not Accesible", HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/validate")
	public ResponseEntity<Object> getValidity(@RequestHeader("Authorization") final String token) {
		String token1 = token.substring(7);
		AuthResponse res = new AuthResponse();
		if (Boolean.TRUE.equals(jwtutil.validateToken(token1))) {
			res.setUid(jwtutil.extractUsername(token1));
			res.setValid(true);
			res.setName(userservice.findById(jwtutil.extractUsername(token1)).get().getUname());
		} else {
			res.setValid(false);
		}
		return new ResponseEntity<>(res, HttpStatus.OK);

	}

}
