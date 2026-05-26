package com.example.PersonalBlog.BlogPackage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponse {
    String generatedToken;

    public AuthResponse(String generatedToken) {
        this.generatedToken = generatedToken;
    }
}
