package com.jp.service;

import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenService {

    private static final long JWT_TOKEN_VALIDITY = 60 * 60 * 1000;
    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public TokenService(@Value("${jwt.secret}") final String secret) {
        System.out.println("JwtTokenService created : secret :" + secret);
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final UserDetails userDetails) {
        System.out.println("JwtTokenService generate");
        String token = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .sign(this.hmac512);
        token = new String(Base64.getEncoder().encodeToString(token.getBytes()));
        return token;
    }

    public String validateTokenAndGetUsername(String token) {
        System.out.println("Token Before : " + token);
        token = new String(Base64.getDecoder().decode(token.getBytes()));

        System.out.println("Token After : " + token);
        System.out.println("JwtTokenService validate");
        try {
            System.out.println("Subject : " + verifier.verify(token).getSubject());
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            log.warn("token invalid: {}", verificationEx.getMessage());
            return null;
        }
    }

}
