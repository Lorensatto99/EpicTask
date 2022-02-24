package br.com.fiap.epictask.controler.api;

import br.com.fiap.epictask.model.Login;
import br.com.fiap.epictask.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/api/auth")
    public ResponseEntity auth(@RequestBody @Valid Login login) {
        Authentication auth = login.getAuthentication();
        try {

            Authentication authentication = authManager.authenticate(auth);

            String token = tokenService.createToken(authentication);

            return ResponseEntity.ok(token);

        } catch (AuthenticationException e) {

            return ResponseEntity.badRequest().build();
        }

    }
}
