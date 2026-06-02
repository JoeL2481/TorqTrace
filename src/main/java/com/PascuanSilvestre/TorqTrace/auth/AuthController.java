package com.PascuanSilvestre.TorqTrace.auth;

import com.PascuanSilvestre.TorqTrace.auth.dto.AuthRequest;
import com.PascuanSilvestre.TorqTrace.auth.dto.AuthResponse;
import com.PascuanSilvestre.TorqTrace.auth.dto.NewAccountRequest;
import com.PascuanSilvestre.TorqTrace.auth.jwt.JwtService;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.PascuanSilvestre.TorqTrace.features.user.user.dto.UserResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody
                                                         AuthRequest authRequest){
        UserDetails user = authService.authenticate(authRequest);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody NewAccountRequest newAccountRequest) {
        return new ResponseEntity<>(userService.save(newAccountRequest), HttpStatus.CREATED);
    }
}
