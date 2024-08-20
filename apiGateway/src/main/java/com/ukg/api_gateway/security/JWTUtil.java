package com.ukg.api_gateway.security;

import com.ukg.api_gateway.helper.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private final SecretKey secret = Jwts.SIG.HS256.key().build();

    public String generateToken(String email, Role role, Long id){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("id", id);
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60 * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
    }

    public String getEmail(String token){
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    public Object getRole(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("role");
    }

    public Object getID(String token){
        Claims claims = extractAllClaims(token);
        return claims.get("id");
    }

    public boolean validateToken(String token) {
        Claims claims = extractAllClaims(token);
        return !isTokenExpired(claims);
    }

    private boolean isTokenExpired(Claims claims) {
        //TODO: remove date and add dateTime
        return claims.getExpiration().before(new Date());
    }
}
