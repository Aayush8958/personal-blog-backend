package com.example.PersonalBlog.BlogPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
 private   String generatedToken;
    private String refreshToken;

    public AuthResponse(String generatedToken,String refreshToken) {
        this.generatedToken = generatedToken;
        this.refreshToken=refreshToken;
    }
}
