package hu.elte.CataflixBackEnd.payload.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username to
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password to
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
