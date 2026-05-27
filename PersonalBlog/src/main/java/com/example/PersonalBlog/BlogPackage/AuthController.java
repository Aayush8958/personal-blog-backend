package com.example.PersonalBlog.BlogPackage;

import com.example.PersonalBlog.Exceptions.AlreadyExists;
import com.example.PersonalBlog.Exceptions.Forbidden;
import com.example.PersonalBlog.Exceptions.NotFound;
import com.example.PersonalBlog.Security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    UserRepo userRepo;
    JwtService jwtService;
    RefreshService refreshService;

    public AuthController(UserRepo userRepo,JwtService jwtService,RefreshService refreshService) {
        this.userRepo = userRepo;
        this.jwtService=jwtService;
        this.refreshService=refreshService;
    }

    @PostMapping("/Login")
    public ResponseEntity<AuthResponse> loginCheck(@RequestBody LoginRequest loginRequest){
        if(!userRepo.existsByUsername(loginRequest.getUsername()))
            throw new NotFound("Invalid username or password");
        Blog_user user= userRepo.findByUsername(loginRequest.getUsername());

        boolean password_check= BCrypt.checkpw(loginRequest.getPassword(),user.getPassword());
        if(!password_check)
            throw new Forbidden("Invalid username or password ");

        String generatedToken= jwtService.generateToken(loginRequest.getUsername());

        RefreshToken refreshToken = refreshService.createRefreshToken(user.getUsername());

        return  ResponseEntity.ok(new AuthResponse(generatedToken, refreshToken.getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshService.findByToken(requestRefreshToken)
                .map(refreshService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String newAccessToken = jwtService.generateToken(user.getUsername());
                    return ResponseEntity.ok(new AuthResponse(newAccessToken, requestRefreshToken));
                })
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        HttpStatus.UNAUTHORIZED, "Refresh token is not in database!"));
    }




    @PostMapping("/register")
    public ResponseEntity<String> RegisterUser(@RequestBody Blog_user author){
        if(userRepo.existsByUsername(author.getUsername())){
            throw new AlreadyExists("UserName Alread exists");
        }
        String pswd=author.getPassword();
        String hash_pswd= BCrypt.hashpw(pswd, BCrypt.gensalt());
        author.setPassword(hash_pswd);

        userRepo.save(author);
        return ResponseEntity.ok("User registered");
    }
}
