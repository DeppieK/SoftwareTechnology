package com.example.demo.security;

import com.example.demo.Roles.Roles;
import com.example.demo.Roles.RolesRepository;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@RestController
//@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    private UserEntity user;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public ModelAndView registrationForm(WebRequest request, Model model) {
        model.addAttribute("user", user);
        return new ModelAndView("redirect:/signUp");
    }

    @GetMapping("/auth/signIn")
    public ModelAndView signInForm() {
        return new ModelAndView("welcomePage");
    }

    @PostMapping("signIn")
    public ModelAndView login(@RequestBody SignInDto signInDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ModelAndView("redirect:/welcomePage");
    }

    @PostMapping("register")
    public ModelAndView register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ModelAndView("signUp");
        }

        // Your registration logic
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Roles roles = rolesRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        // After successful registration, you can return a success message
        return new ModelAndView("redirect:/welcomePage"); // Redirect to "welcomePage"
    }

    }

