package api.ahm.motogp.identity.domain.model;

import api.ahm.motogp.identity.domain.model.valueobjects.Email;
import api.ahm.motogp.identity.domain.model.valueobjects.Username;

public class User {
    private final Long id;
    private Username username;
    private Email email;
    private String password;
    private Role role;

    public enum Role {
        ADMIN,
        USER
    }

    private User(Long id, Username username, Email email, String password, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static User create(String username, String email, String password, String role) {
        return new User(
                0L,
                new Username(username),
                new Email(email),
                password,
                toRole(role)
        );
    }

    public static User fromPersistence(Long id, String username, String email, String password, String role) {
        return new User(
                id,
                new Username(username),
                new Email(email),
                password,
                toRole(role)
        );
    }

    private static Role toRole(String role) {
        if (role == null || role.isBlank()) {
            return Role.USER;
        }
        return Role.valueOf(role.trim().toUpperCase());
    }

    public Long getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
