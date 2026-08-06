package api.ahm.motogp.identity.infrastructure.adapter.in.rest;

import api.ahm.motogp.identity.application.port.in.CreateUserUseCase;
import api.ahm.motogp.identity.application.port.in.ListUserUseCase;
import api.ahm.motogp.identity.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUserUseCase listUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase,
                          ListUserUseCase listUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listUserUseCase = listUserUseCase;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = listUserUseCase.getUsers()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable int id) {
        Optional<UserResponse> userResponse = listUserUseCase.getUser(id).map(UserMapper::toResponse);
        return userResponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest userRequest,
                                                   UriComponentsBuilder ucb) {
        User user = createUserUseCase.createUser(UserMapper.toCommand(userRequest));
        URI location = ucb.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(UserMapper.toResponse(user));
    }
}
