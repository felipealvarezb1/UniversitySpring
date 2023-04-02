package felipe.springframework.university.web.controller;

import felipe.springframework.university.common.dto.AuthResponseDto;
import felipe.springframework.university.common.dto.LoginDto;
import felipe.springframework.university.common.dto.RegisterDto;
import felipe.springframework.university.domain.model.Role;
import felipe.springframework.university.domain.model.UserEntity;
import felipe.springframework.university.persistence.repository.RoleRepository;
import felipe.springframework.university.persistence.repository.UserRepository;
import felipe.springframework.university.security.JWTGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTGenerator jwTgenerator;

    @PostMapping(path = "login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwTgenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping(path = "register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (userRepository.existsByUsername(registerDto.getUsername())){
            return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER");
        userEntity.setRoles(Collections.singletonList(roles));

        userRepository.save(userEntity);

        return new ResponseEntity<>("User registered success", HttpStatus.OK);

    }

    @GetMapping(path = "logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<>("User logout success", HttpStatus.OK);
    }
}
