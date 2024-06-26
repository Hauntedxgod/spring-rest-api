package ru.maxima.springrestapi;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.time.ZonedDateTime;
import java.util.Date;

public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;

    public String generateToken(String username){

        Date expireDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        return JWT.create()
                .withSubject("User details")
                .withClaim("username" , username)
                .withIssuedAt(new Date())
                .withIssuer("Maxima_School")
                .withExpiresAt(expireDate)
                .sign(Algorithm.HMAC256(secret));

    }

    public String validateToken(String token){

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("Maxima_School")
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getClaim("username").asString();

    }

}
