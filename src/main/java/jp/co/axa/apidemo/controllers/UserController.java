package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.User;
import jp.co.axa.apidemo.repositories.UserRepository;
import jp.co.axa.apidemo.security.UsernameAndPasswordAuthenticationRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user")
    public void createUser(@RequestBody UsernameAndPasswordAuthenticationRequest request) {
        User user = new User();
        user.setLoginId(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        log.info("recieving create user: {}", user.getLoginId());
        userRepository.save(user);
    }
}
