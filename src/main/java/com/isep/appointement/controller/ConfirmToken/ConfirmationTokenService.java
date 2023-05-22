package com.isep.appointement.controller.ConfirmToken;

import com.isep.appointement.Repository.ConfirmationTokenRepository;
import com.isep.appointement.model.ConfirmationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class ConfirmationTokenService {

    private final ConfirmationTokenRepository repository;

    public ConfirmationTokenService(ConfirmationTokenRepository repository) {
        this.repository = repository;
    }

    public void saveConfirmationToken(ConfirmationToken token){
        repository.save(token);
    }
    public Optional<ConfirmationToken> getToken(String token) {
        return repository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return repository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
