package rest;

import hibernate.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.dto.SignUpFormDto;
import rest.security.SecurityService;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private SecurityService securityService;
    private UserService userService;

    @Autowired
    public UserController(SecurityService securityService, UserService userService) {
        this.securityService = securityService;
        this.userService = userService;
        LOGGER.info("User Controller Created!");
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) {
        System.out.println(login + " " + password);
        try {
            return ResponseEntity.ok().body(securityService.authorize(login, password));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody SignUpFormDto signUpRequest) {
        try {
            userService.createUser(
                signUpRequest.getLogin(),
                signUpRequest.getFirstName(),
                signUpRequest.getPatronymic(),
                signUpRequest.getLastName(),
                signUpRequest.getPassword(),
                signUpRequest.getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("User registered successfully!");
    }

    @GetMapping("/profile")
    public ResponseEntity<String> profile(@RequestParam String token) {
        try {
            System.out.println(securityService.getUserByToken(token));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().body("Complete!");
    }
}
