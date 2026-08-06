package api.ahm.motogp.identity.domain.model.valueobjects;

import api.ahm.motogp.identity.domain.exception.InvalidUsernameException;

public record Username(String username) {
    public Username {
        if (username == null || username.isBlank()) {
            throw new InvalidUsernameException("Username cannot be empty");
        }

        String normalized = username.trim().toLowerCase();

        if (normalized.length() < 3 || normalized.length() > 30) {
            throw new InvalidUsernameException(
                    "Username must contain between 3 and 30 characters"
            );
        }

        if (!normalized.matches("^[a-z0-9._-]+$")) {
            throw new InvalidUsernameException(
                    "Username contains invalid characters"
            );
        }

        username = normalized;
    }
}
