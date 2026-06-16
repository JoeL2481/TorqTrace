package com.PascuanSilvestre.TorqTrace.config;

import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsEntity;
import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsRepository;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.Roles;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final CredentialsRepository credentialsRepository;

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof String username) {
            return username;
        }

        throw new IllegalStateException("Authenticated principal is not a username");
    }

    public CredentialsEntity getCurrentCredentials() {
        String username = getCurrentUsername();

        return credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));
    }

    public UserEntity getCurrentUser() {
        return getCurrentCredentials().getUsuario();
    }
    public Long getCurrentUserId() {
        return getCurrentCredentials().getUsuario().getId();
    }

    public boolean hasRole(Roles role) {
        return getCurrentCredentials()
                .getRoles()
                .stream()
                .anyMatch(roleEntity -> roleEntity.getRole() == role);
    }
}
