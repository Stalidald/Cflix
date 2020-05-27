package hu.elte.CataflixBackEnd.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    /**
     * Constructor of JwtResponse using the following parameters
     * @param accessToken
     * @param id
     * @param username
     * @param email
     * @param roles
     */
    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    /**
     * @return token
     */
    public String getAccessToken() {
        return token;
    }

    /**
     * Set responses access token to
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    /**
     * @return type
     */
    public String getTokenType() {
        return type;
    }

    /**
     * Set responses token type to
     * @param tokenType
     */
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set responses id to
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set responses email to
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set responses username to
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return roles
     */
    public List<String> getRoles() {
        return roles;
    }
}
