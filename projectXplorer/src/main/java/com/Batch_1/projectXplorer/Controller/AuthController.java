package com.Batch_1.projectXplorer.Controller;

import com.Batch_1.projectXplorer.DTO.AuthResponse;
import com.Batch_1.projectXplorer.DTO.ErrorResponse;
import com.Batch_1.projectXplorer.DTO.LoginRequest;
import com.Batch_1.projectXplorer.DTO.RegisterRequest;
import com.Batch_1.projectXplorer.Entity.Role;
import com.Batch_1.projectXplorer.Entity.Users;
import com.Batch_1.projectXplorer.Repository.UserRepository;
import com.Batch_1.projectXplorer.Security.JwtService;
import com.Batch_1.projectXplorer.Service.JpaUserDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository users;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JpaUserDetailsService uds;
    private final JwtService jwt;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r) {

        // Check username and email existence
        boolean usernameExists = users.existsByUsername(r.getUsername());
        boolean emailExists = users.existsByEmail(r.getEmail());

        // Determine the conflict message if any
        String conflictMessage = null;
        if (usernameExists && emailExists) conflictMessage = "USERNAME_AND_EMAIL_EXISTS";
        else if (usernameExists) conflictMessage = "USERNAME_EXISTS";
        else if (emailExists) conflictMessage = "EMAIL_EXISTS";

        // If conflict exists, return immediately
        if (conflictMessage != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(conflictMessage));
        }

        // No conflict, create user
        Users u = new Users();
        u.setUsername(r.getUsername());
        u.setEmail(r.getEmail());
        u.setPassword(encoder.encode(r.getPassword()));
        u.setRole(Role.user);
        users.save(u);

        // Return success message
        return ResponseEntity.ok(new AuthResponse(null, u.getUsername(), u.getRole().name(), "Account created successfully"));
    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest r) {
        UsernamePasswordAuthenticationToken authReq =
                new UsernamePasswordAuthenticationToken(r.getUsernameOrEmail(), r.getPassword());

        try {
            authManager.authenticate(authReq);
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Users u = users.findByUsername(r.getUsernameOrEmail())
                .or(() -> users.findAll().stream()
                        .filter(x -> x.getEmail().equalsIgnoreCase(r.getUsernameOrEmail()))
                        .findFirst())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserDetails ud = uds.loadUserByUsername(u.getUsername());
        String token = jwt.generateToken(ud, u); // ✅ token generated here

        // ✅ Return token in response
        return ResponseEntity.ok(new AuthResponse(token, u.getUsername(), u.getRole().name(), "Login successful"));
    }


//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest r) {
//        System.out.println("Login attempt: " + r.getUsernameOrEmail());
//
//        UsernamePasswordAuthenticationToken authReq =
//                new UsernamePasswordAuthenticationToken(r.getUsernameOrEmail(), r.getPassword());
//        try {
//            authManager.authenticate(authReq);
//            System.out.println("AuthenticationManager passed for user: " + r.getUsernameOrEmail());
//        } catch (AuthenticationException ex) {
//            System.out.println("Authentication failed: " + ex.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        Users u = users.findByUsername(r.getUsernameOrEmail())
//                .or(() -> users.findAll().stream()
//                        .filter(x -> x.getEmail().equalsIgnoreCase(r.getUsernameOrEmail())).findFirst())
//                .orElseThrow(() -> {
//                    System.out.println("User not found for: " + r.getUsernameOrEmail());
//                    return new UsernameNotFoundException("Not found");
//                });
//        System.out.println("User found: " + u.getUsername() + ", role: " + u.getRole().name());
//
//        UserDetails ud = uds.loadUserByUsername(u.getUsername());
//        System.out.println("UserDetails loaded for: " + ud.getUsername());
//
//        String token = jwt.generateToken(ud, u);
//        System.out.println("Generated JWT: " + token);
//
//        return ResponseEntity.ok(new AuthResponse(token, u.getUsername(), u.getRole().name()));
//    }

}

