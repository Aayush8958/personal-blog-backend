package com.example.PersonalBlog.BlogPackage;

import com.example.PersonalBlog.Exceptions.NotFound;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshService {
private RefreshTokenRepository refreshTokenRepository;
private UserRepo userRepo;

    public RefreshService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }
    @Transactional
    public RefreshToken createRefreshToken(String username) {
        if(!userRepo.existsByUsername(username)){
            throw new NotFound("User Not found");
        }

        Blog_user user = userRepo.findByUsername(username);

        refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(1000L * 60 * 60 * 24 * 7));

        return refreshTokenRepository.save(refreshToken);
    }
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.UNAUTHORIZED,
                    "Refresh token was expired. Please sign in again."
            );
        }
        return token;
    }
        public Optional<RefreshToken> findByToken(String token) {
            return refreshTokenRepository.findByToken(token);
        }



}
