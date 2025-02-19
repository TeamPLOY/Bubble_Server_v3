package com.ploy.bubble_server_v3.domain.auth.service.implementation;

import com.ploy.bubble_server_v3.domain.auth.domain.Token;
import com.ploy.bubble_server_v3.domain.auth.domain.repository.TokenRepository;
import com.ploy.bubble_server_v3.domain.auth.exception.EmailAlreadyExistsException;
import com.ploy.bubble_server_v3.domain.auth.exception.EmailNotFoundException;
import com.ploy.bubble_server_v3.domain.auth.exception.TokenNotFoundException;
import com.ploy.bubble_server_v3.domain.user.domain.Users;
import com.ploy.bubble_server_v3.domain.user.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthReader {
    private final UsersRepository usersRepository;
    private final TokenRepository tokenRepository;

    public boolean isEmailExist(String email) {
        if (usersRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException();
        }
        return false;
    }

    public Token findTokenByUserAndDeviceToken(Users user, String deviceToken) {
        return tokenRepository.findByUserIdAndDeviceToken(user.getId(), deviceToken)
                .orElseGet(() -> Token.builder()
                        .user(user)
                        .deviceToken(deviceToken)
                        .build());
    }

    public Token findByRefreshToken(String refreshToken) {
        return tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenNotFoundException());
    }
}