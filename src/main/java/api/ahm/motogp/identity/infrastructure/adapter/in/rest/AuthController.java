package api.ahm.motogp.identity.infrastructure.adapter.in.rest;

import api.ahm.motogp.identity.application.port.in.LoginUseCase;
import api.ahm.motogp.identity.application.port.out.AccessToken;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AccessToken accessToken = loginUseCase.login(AuthMapper.toCommand(loginRequest));
        return ResponseEntity.ok(AuthMapper.toResponse(accessToken));
    }
}
