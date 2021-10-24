package com.challengeWarmUp.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.challengeWarmUp.challenge.daoImp.User;
import com.challengeWarmUp.challenge.dto.AuthRequest;
import com.challengeWarmUp.challenge.dto.AuthResponse;
import com.challengeWarmUp.challenge.security.UserDetailsServiceImp;
import com.challengeWarmUp.challenge.util.JwtUtil;

@RestController
public class UserController {

  @Autowired
  private User user;

  @Autowired
  private AuthenticationManager authManager;

  @Autowired
  private UserDetailsServiceImp userDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping(value = "auth/log_in")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest)
      throws Exception {

    try {
      authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
          authRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }
    final UserDetails userDetails =
        this.userDetailsService.loadUserByUsername(authRequest.getEmail());
    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthResponse(jwt));
  }

  @PostMapping(value = "auth/sign_up")
  public ResponseEntity<?> register(@RequestBody com.challengeWarmUp.challenge.models.User user) {

    if (user.getEmail() == null || user.getEmail().isEmpty() || user.getPassword() == null
        || user.getEmail() == null)
      return ResponseEntity.badRequest().body("Fields cannot be null or empty");

    if (this.user.findByEmail(user.getEmail()) != null) {
      return ResponseEntity.badRequest().body("User alredy exists");
    }

    this.user.create(user);
    return ResponseEntity.ok("User has been register");
  }
}
