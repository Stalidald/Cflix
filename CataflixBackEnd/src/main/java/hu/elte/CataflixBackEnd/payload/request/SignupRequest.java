package hu.elte.CataflixBackEnd.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set Signup Requests username to
     * @param userName
     */
    public void setUsername(String userName) {
        this.username = userName;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set Signup Requests email to
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Signup Requests password to
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return role
     */
    public Set<String> getRole() {
        return this.role;
    }

    /**
     * Set Signup Requests role to
     * @param role
     */
    public void setRole(Set<String> role) {
        this.role = role;
    }
}
