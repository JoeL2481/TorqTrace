package com.PascuanSilvestre.TorqTrace.auth.config;

import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public Long getCurrentUserId() {
        CredentialsEntity credentials =
                (CredentialsEntity) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return credentials.getUsuario().getId();
    }

    public UserEntity getCurrentUser() {
        CredentialsEntity credentials =
                (CredentialsEntity) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        return credentials.getUsuario();
    }
}
