package api.ahm.motogp.identity.domain.model.valueobjects;

import api.ahm.motogp.identity.domain.exception.InvalidEmailException;

import java.util.regex.Pattern;

public record Email(String email) {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");

    public Email {
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException();
        }

        String normalized = email.trim().toLowerCase();

        if (!EMAIL_PATTERN.matcher(normalized).matches()) {
            throw new InvalidEmailException();
        }

        email = normalized;
    }
}
