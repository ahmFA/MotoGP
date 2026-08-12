package api.ahm.motogp.identity.infrastructure.adapter.out.persistence;

import api.ahm.motogp.identity.application.port.out.UserRepositoryPort;
import api.ahm.motogp.identity.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final SpringDataUserRepository userRepository;

    public UserPersistenceAdapter(SpringDataUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepository.findById(id).map(this::toDomain);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public User createUser(User user) {
        UserJPAEntity entity = new UserJPAEntity();
        entity.setId(0L);
        entity.setUsername(user.getUsername().username());
        entity.setEmail(user.getEmail().email());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole().name());

        return toDomain(userRepository.save(entity));
    }

    private User toDomain(UserJPAEntity entity) {
        return User.fromPersistence(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }
}
