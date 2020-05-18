package hu.elte.CataflixBackEnd.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.elte.CataflixBackEnd.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor for UserDetailsImpl instance using the following parameters
     * @param id
     * @param username
     * @param email
     * @param password
     * @param authorities
     */
    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Creates UserDetailsImpl instance based on user
     * @param user
     * @return UserDetailsImpl instance
     */
    public static UserDetailsImpl build(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }

    /**
     * @return users authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * @return users id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return users password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * @return users username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * @return users expiration status
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return users lock status
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return user credentials expiration status
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return users enable status
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Checks Equality with another object
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}