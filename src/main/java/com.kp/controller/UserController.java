package com.kp.controller;

import com.kp.model.user.User;
import com.kp.rest.TokenUtils;
import com.kp.security.MyUserDetailsService;
import com.kp.service.UserService;
import com.kp.transfer.TokenTransfer;
import com.kp.transfer.UserTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.WebApplicationException;


/**
 * Created by user on 8/17/16.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserService userService;

    private Authentication authentication;

    private Authentication getAuthentication(){
        Authentication temp=this.authentication;
        this.authentication=null;
        return  temp;
    }

    private void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    @Autowired
    MyUserDetailsService myUserDetailService;

    @Autowired
    private AuthenticationManager authManager;



    @RequestMapping(value = "/user/authenticate", method = RequestMethod.POST)
    public TokenTransfer authenticate(@FormParam("email") String email, @FormParam("password") String password, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        UserDetails userDetails = this.myUserDetailService.loadUserByUsername(email);

        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }

//    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
//    public ResponseEntity<Void> register(@FormParam("username") String username, @FormParam("email") String email, @FormParam("password") String password)
//    {
//        User user = UserService.findByUsername(username);
//        if (user!=null) {
//            System.out.println("A User with name " + user.getUsername() + " already exist");
//            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
//        }
//
//        user=new User(username,passwordEncoder.encode(password));
//        user.setAvatarUrl("http://lorempixel.com/300/300/");
//        user.setEmail(email);
//        user.addRole(Role.USER);
//        UserService.saveUser(user);
//
//        return new ResponseEntity<Void>(HttpStatus.CREATED);
//    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserTransfer getUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Object principal = authentication.getPrincipal();
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof String && (principal).equals("anonymousUser")) {
            throw new WebApplicationException(401);
        }
        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) principal;
        //ExampleUserDetails userDetails = (ExampleUserDetails) principal;

        return new UserTransfer(userDetails.getUsername());
    }

}


