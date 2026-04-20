package com.atlen.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public class AuthDto {
    
    @Data
    public static class LoginRequest {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }

    @Data
    public static class RegisterRequest {
        @NotBlank
        private String firstName;
        @NotBlank
        private String lastName;
        @NotBlank @Email
        private String email;
        @NotBlank
        private String password;
    }

    @Data
    public static class AuthResponse {
        private String token;
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;

        public AuthResponse(String token, Long userId, String firstName, String lastName, String email) {
            this.token = token;
            this.userId = userId;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
    }
}
