package com.example.PersonalBlog.BlogPackage;

import com.example.PersonalBlog.Exceptions.AlreadyExists;
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

    public AuthController(UserRepo userRepo) {
        this.userRepo = userRepo;
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
