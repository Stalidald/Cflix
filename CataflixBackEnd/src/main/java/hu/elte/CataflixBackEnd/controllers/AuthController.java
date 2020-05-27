package hu.elte.CataflixBackEnd.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import hu.elte.CataflixBackEnd.entities.AchivementEntity;
import hu.elte.CataflixBackEnd.entities.UserEntity;
import hu.elte.CataflixBackEnd.models.ERole;
import hu.elte.CataflixBackEnd.models.Role;
import hu.elte.CataflixBackEnd.payload.request.LoginRequest;
import hu.elte.CataflixBackEnd.payload.request.SignupRequest;
import hu.elte.CataflixBackEnd.payload.response.JwtResponse;
import hu.elte.CataflixBackEnd.payload.response.MessageResponse;
import hu.elte.CataflixBackEnd.repositories.RoleRepository;
import hu.elte.CataflixBackEnd.repositories.UserRepository;
import hu.elte.CataflixBackEnd.security.jwt.JwtUtils;
import hu.elte.CataflixBackEnd.security.services.UserDetailsImpl;
import hu.elte.CataflixBackEnd.services.AchivementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AchivementService achivementService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Checks recieved authentication data against data in database
     * @param loginRequest object
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    /**
     * Registers a user, checking if inserted data is taken already, storing it in database otherwise.
     * @param signUpRequest object
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Hiba: ez a felhasználónév már foglalt!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Hiba: ez az email már foglalt!"));
        }

        // Create new user's account
        UserEntity user = new UserEntity(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "prenium":
                        Role premiumRole = roleRepository.findByName(ERole.ROLE_PREMIUM)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(premiumRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        List<AchivementEntity> unlockedAchivements = new ArrayList<>();
        unlockedAchivements.add(achivementService.loadDataById(Long.valueOf(1)));
        user.unlockedAchivements = unlockedAchivements;
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Sikeres regisztráció"));
    }
}
