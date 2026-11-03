package api.ahm.motogp.identity.infrastructure.adapter.out.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtConfig {

    @Bean
    public JwtEncoder jwtEncoder(
            @Value("${spring.security.oauth2.resourceserver.jwt.public-key-location}") RSAPublicKey publicKey,
            @Value("${jwt.private-key-location}") RSAPrivateKey privateKey) {
        return NimbusJwtEncoder.withKeyPair(publicKey, privateKey).build();
    }
}
