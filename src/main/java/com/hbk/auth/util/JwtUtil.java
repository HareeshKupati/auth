package com.hbk.auth.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Date;

public class JwtUtil {

    public static final String KEY = "secret";

    public static String generateToken(Claims claims) {
        return Jwts.builder().
                setClaims(claims).
                signWith(SignatureAlgorithm.HS256, KEY).compact();
    }

    public static String generateToken(UserDetails userDetails) {
       return generateToken(Jwts.claims().setIssuer("GOD").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration( new Date(System.currentTimeMillis() + 600000)).setSubject(userDetails.getUsername()));
    }

    public static String getSubject(String token) {
        if(validateToken(token)){
            return getClaims(token).getSubject();
        }
        return null;
    }

    public static boolean validateToken(String token) {
        Claims claims = claims = getClaims(token);
        System.out.println((Integer)claims.get(Claims.EXPIRATION) + "-" +System.currentTimeMillis());
        return claims !=null && (Integer)claims.get(Claims.EXPIRATION) > (System.currentTimeMillis()/1000);
    }

    public static Claims getClaims(String token){
        return (Claims) Jwts.parser().setSigningKey(KEY).parse(token).getBody();
    }
}
