package api.ahm.motogp.identity.infrastructure.adapter.out.security;

import api.ahm.motogp.identity.application.port.out.AccessToken;
import api.ahm.motogp.identity.application.port.out.TokenGeneratorPort;
import api.ahm.motogp.identity.domain.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class JwtTokenAdapter implements TokenGeneratorPort {

    private final JwtEncoder jwtEncoder;
    private final String issuer;
    private final Duration expiration;

    public JwtTokenAdapter(JwtEncoder jwtEncoder,
                           @Value("${jwt.issuer}") String issuer,
                           @Value("${jwt.expiration}") Duration expiration) {
        this.jwtEncoder = jwtEncoder;
        this.issuer = issuer;
        this.expiration = expiration;
    }

    @Override
    public AccessToken generate(User user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .subject(String.valueOf(user.getId()))
                .issuedAt(now)
                .expiresAt(now.plus(expiration))
                .claim("username", user.getUsername().username())
                .claim("role", user.getRole().name())
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new AccessToken(token, expiration.toSeconds());
    }
}
