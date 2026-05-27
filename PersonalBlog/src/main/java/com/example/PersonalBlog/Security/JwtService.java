package com.example.PersonalBlog.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    private static final String Secret_Key="9a4f2c8d3e1b7a6f5c4d3b2a1e0f9c8b7a6f5e4d3c2b1a0f9e8d7c6b5a4f3e2d";

        private SecretKey getSingningKey(){
            byte[] keybytes= Decoders.BASE64.decode(Secret_Key);
            return Keys.hmacShaKeyFor(keybytes);
        }

        public String generateToken(String username){
            return Jwts.builder()
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() +(60*1000*60)))
                    .signWith(getSingningKey())
                    .compact();
        }
        public String extractUserName(String token){
            return Jwts.parser()
                    .verifyWith(getSingningKey())
                    .build().parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        }
}
