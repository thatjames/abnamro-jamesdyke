package xyz.slimjim.hungrytales.service.auth;

import xyz.slimjim.hungrytales.common.auth.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class AuthToken {

    private User user;
    private LocalDateTime validUntil;

    public AuthToken(User user) {
        this.user = user;
        validUntil = LocalDateTime.now().plus(2, ChronoUnit.HOURS);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public boolean isTTLValid() {
        return validUntil.isAfter(LocalDateTime.now());
    }
}
