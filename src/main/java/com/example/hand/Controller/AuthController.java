package com.example.hand.Controller;

import com.example.hand.Dto.AuthRequest;
import com.example.hand.Dto.AuthResponse;
import com.example.hand.Dto.sellerDto;
import com.example.hand.Repository.sellerRepository;
import com.example.hand.Service.MapStuctMapper;
import com.example.hand.config.JwtUtil;
import com.example.hand.model.seller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final sellerRepository sellerRepository;
    private final MapStuctMapper mapStuctMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(sellerRepository sellerRepository,
                          MapStuctMapper mapStuctMapper,
                          JwtUtil jwtUtil,
                          AuthenticationManager authenticationManager) {
        this.sellerRepository = sellerRepository;
        this.mapStuctMapper = mapStuctMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = sellerRepository.findByEmail(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        seller s = sellerRepository.findByEmail(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, s.getEmail(), s.getNameSeller(), s.getId()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody sellerDto sellerDto) {
        if (sellerRepository.findByEmail(sellerDto.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "כתובת האימייל כבר קיימת במערכת"));
        }
        seller newSeller = mapStuctMapper.toSeller(sellerDto);
        seller saved = sellerRepository.save(newSeller);
        UserDetails userDetails = sellerRepository.findByEmail(saved.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(token, saved.getEmail(), saved.getNameSeller(), saved.getId()));
    }
}
