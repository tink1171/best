package com.kp.controller;

import com.kp.dto.ExampleUserDetails;
import com.kp.errors.UserAlreadyExistException;
import com.kp.events.OnRegistrationCompleteEvent;
import com.kp.model.user.User;
import com.kp.model.verification_token.VerificationToken;
import com.kp.repository.RoleRepository;
import com.kp.repository.UserRepository;
import com.kp.repository.VerificationTokenRepository;
import com.kp.rest.TokenUtils;
import com.kp.security.MyUserDetailsService;
import com.kp.service.UserService;
import com.kp.transfer.TokenTransfer;
import com.kp.transfer.UserTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.FormParam;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by user on 8/17/16.
 */
@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MessageSource messages;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    private Authentication authentication;

    private Authentication getAuthentication(){
        Authentication temp = this.authentication;
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
    public TokenTransfer authenticate(@FormParam("userName") String userName, @FormParam("password") String password, HttpServletRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName, password);
        Authentication authentication = this.authManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        LOGGER.error("FIRSTNAME : " + userName + password);

        UserDetails userDetails = this.myUserDetailService.loadUserByUsername(userName);

        return new TokenTransfer(TokenUtils.createToken(userDetails));
    }


    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@FormParam("user_name")final String user_name,
                                         @FormParam("first_name")final  String first_name,
                                         @FormParam("last_name")final String last_name,
                                         @FormParam("email")final String email,
                                         @FormParam("password")final String password,
                                         HttpServletRequest request)
    {
        User user = userService.findByEmail(email);
        if (user!=null) {
            System.out.println("A User with name " + user.getUsername() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        User registered = new User();

        registered.setFirstName(first_name);
        registered.setLastName(last_name);
        registered.setUsername(user_name);
        registered.setEmail(email);
        registered.setAvatarUrl("http://i1.wp.com/wp-kama.ru/wp-content/themes/wp-kama/img/no_ava.png");
        registered.setPassword(password);
        registered.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userService.saveRegisteredUser(registered);

        try {
            final String appUrl = request.getContextPath();
            LOGGER.debug("URL: " + appUrl);
            applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (final Exception ex) {
            LOGGER.warn("Unable to register user", ex);
            //return new ModelAndView("registration", "user", accountDto);
        }

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserTransfer getUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        //Object principal = getAuthentication().getPrincipal();
        if (principal instanceof String && (principal).equals("anonymousUser")) {
            throw new WebApplicationException(401);
        }
        ExampleUserDetails userDetails = (ExampleUserDetails) principal;
        //ExampleUserDetails userDetails = (ExampleUserDetails) principal;
        return new UserTransfer(userDetails.getId(),userDetails.getAvatarUrl(),userDetails.getUsername());
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(){
        return (userRepository.findAll().toString());
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public void confirmRegistration (final HttpServletRequest request, final Model model,
                                       @RequestParam("token") final String token,
                                       HttpServletResponse response) {
        LOGGER.debug("confirm registartion for enable user acc !!!");
        Locale locale = request.getLocale();
        LOGGER.debug("TOKEN : " + token);
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
//            return "redirect:/badUser";
        }

        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
//            return "redirect:/badUser";
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        LOGGER.debug("INFO : " + user.toString());
        try {
            response.sendRedirect("/#/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("name") String userName) {
        System.out.println("Fetching User with id " + userName);
        User user = userService.findByUsername(userName);
        if (user == null) {
            System.out.println("User with id " + userName + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}


