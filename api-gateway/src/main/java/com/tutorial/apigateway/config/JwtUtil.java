package com.tutorial.apigateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtUtil {
    private final static String SECRET_KEY = "jdwmrCbaWEskmog3IiOXFoQrmmHDzqFB2ZWNdGHWDng=";

    private final SecretKey key;

    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY));
    }

    public void validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
