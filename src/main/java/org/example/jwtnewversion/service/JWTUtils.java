package org.example.jwtnewversion.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    private static final String key="encode_token_now";

    private static final long EXPIRATION=60*60*24*1000; //24 hours

    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }
    //Maybe use refresh token with down code part

//    public String generateRefreshToken(HashMap<String, Object> claims,UserDetails userDetails){
//        return Jwts.builder()
//                .claims(claims)
//                .subject(userDetails.getUsername())
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
//                .signWith(SignatureAlgorithm.HS256,key)
//                .compact();
//    }

    public boolean tokenValidate(String token) {
        return getUsernameToken(token) != null && isExpired(token);
    }

    public String getUsernameToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getPayload();
    }

    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }
}
