package com.ikadev.daybydayplanner.endpoints;

import com.ikadev.daybydayplanner.persistence.model.User;
import com.ikadev.daybydayplanner.service.TokenService;
import com.ikadev.daybydayplanner.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/auth")
public class AuthEndpoint {
    private final TokenService tokenService;
    private final UserService userService;

    public AuthEndpoint(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @PostMapping("register")
    public User registerUser(@RequestBody @Validated User user) {
        if (userService.usernameExists(user.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "The username " + user.getUsername() + " already exists!"
            );
        }
        return userService.save(user);
    }

    @PostMapping("login")
    public String login(Authentication authentication) {
        return tokenService.generateToken(authentication);
    }


}