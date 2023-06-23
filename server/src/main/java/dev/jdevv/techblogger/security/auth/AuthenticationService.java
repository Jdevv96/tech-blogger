package dev.jdevv.techblogger.security.auth;


import dev.jdevv.techblogger.entity.Role;
import dev.jdevv.techblogger.entity.User;
import dev.jdevv.techblogger.repository.UserRepository;
import dev.jdevv.techblogger.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest requestToRegister) {
        var user = User.builder()
                .firstName(requestToRegister.getFirstName())
                .lastName(requestToRegister.getLastName())
                .userName(requestToRegister.getUserName())
                .password(passwordEncoder.encode(requestToRegister.getPassword()))
                .role(requestToRegister.getRole())
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest requestToAuthenticate) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestToAuthenticate.getUserName(),
                        requestToAuthenticate.getPassword()
                )
        );
        var user = repository.findByUserName(requestToAuthenticate.getUserName()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }




}
