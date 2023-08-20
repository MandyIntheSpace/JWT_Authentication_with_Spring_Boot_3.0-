package com.example.jwtauthentication.config;

import com.example.jwtauthentication.contant.ApiConstant;
import com.example.jwtauthentication.models.JwtRequest;
import com.example.jwtauthentication.models.JwtResponse;
import com.example.jwtauthentication.security.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(ApiConstant.AUTH)
public class AuthContoller {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper jwtHelper;

    private Logger logger = LoggerFactory.getLogger(AuthContoller.class);

    @PostMapping(ApiConstant.LOGIN)
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
        this.doAuthenticate(jwtRequest.getEmail(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtHelper.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return response != null ? new ResponseEntity<JwtResponse>(response, HttpStatus.OK)
                : new ResponseEntity<JwtResponse>(response, HttpStatus.NOT_FOUND);
    }

    public void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        try{
            this.manager.authenticate(authenticationToken);
        } catch(BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username and Password ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return " Invalid Credentials !! ";
    }

}
