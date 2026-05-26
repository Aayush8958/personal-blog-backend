package com.example.PersonalBlog.BlogPackage;

import com.example.PersonalBlog.Exceptions.AlreadyExists;
import com.example.PersonalBlog.Exceptions.Forbidden;
import com.example.PersonalBlog.Exceptions.NotFound;
import com.example.PersonalBlog.Security.JwtService;
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

    public AuthController(UserRepo userRepo,JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService=jwtService;
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

        return  ResponseEntity.ok(new AuthResponse(generatedToken));
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
