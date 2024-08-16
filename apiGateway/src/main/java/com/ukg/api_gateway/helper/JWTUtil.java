package com.ukg.api_gateway.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    private SecretKey secret = Jwts.SIG.HS256.key().build();

    public String generateToken(String email, Role role){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
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
//        System.out.println(claims.get("role").getClass());
        return claims.get("role");
    }
}
