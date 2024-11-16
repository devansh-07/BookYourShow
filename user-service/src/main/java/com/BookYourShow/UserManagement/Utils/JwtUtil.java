package com.BookYourShow.UserManagement.Utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;


@Component
public class JwtUtil {

    private final String KEY = "myBYS";
    private final Long EXPIRATION_TIME = 86400000L;

    
    private Algorithm getAlgorithm() {
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        return algorithm;
    }


    public String createToken(String email) {
        Algorithm algorithm = getAlgorithm();

        String token = JWT.create()
            .withClaim("email", email)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(algorithm);
        
        return token;
    }


    public String extractEmail(DecodedJWT decodedJWT) {
        return decodedJWT.getClaim("email").asString();
    }


    public DecodedJWT decodeToken(String token) {
        try {
            Algorithm algorithm = getAlgorithm();
            JWTVerifier jwtVerifier = JWT.require(algorithm).build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return decodedJWT;
        } catch (Exception ex) {
            return null;
        }
    }

}
