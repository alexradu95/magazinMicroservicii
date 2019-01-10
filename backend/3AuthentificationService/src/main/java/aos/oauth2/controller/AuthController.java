package aos.oauth2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aos.oauth2.payload.LoginRequest;
import aos.oauth2.payload.SignUpRequest;
import aos.oauth2.service.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {    
    
    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    	return userService.loginUser(loginRequest);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
          return userService.createUser(signUpRequest);

    }
}
