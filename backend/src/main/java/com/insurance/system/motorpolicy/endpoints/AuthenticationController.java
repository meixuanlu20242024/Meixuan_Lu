package com.insurance.system.motorpolicy.endpoints;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {




//    @Autowired
//    private UserDetailsService userDetailsService;

//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//        );
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }

//    @PostMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
//        String username = jwtUtil.extractUsername(refreshTokenRequest.getToken());
//        if (jwtUtil.validateToken(refreshTokenRequest.getToken(), username)) {
//            final String jwt = jwtUtil.generateToken(username);
//            return ResponseEntity.ok(new AuthenticationResponse(jwt));
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Refresh Token");
//    }
}
