
package com.asher.hospitalmanagement.dto.response;

/**
 * Authentication response containing the generated JWT.
 */
public class AuthResponse {

    private String token;
    private String tokenType;
    private Long expiresIn;

    public AuthResponse() {
    }

    public AuthResponse(String token, String tokenType, Long expiresIn) {
        this.token = token;
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
