package org.example.doctorreservation.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${expire}")
    private Long expire;
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public  String generateToken(String username) {
        return Jwts.builder().subject(username).signWith(getSecretKey()).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+expire)).compact();
    }
    public String extractUsername(String token) {
        return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload().getSubject();
    }
    public Date extractExpiration(String token) {
        return Jwts.parser().verifyWith( getSecretKey()).build().parseSignedClaims(token).getPayload().getExpiration();
    }
    public boolean validateToken(String token, String username) {
        return  extractUsername(token).equals(username) && extractExpiration(token).after(new Date());
    }
}
