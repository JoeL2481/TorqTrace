package com.PascuanSilvestre.TorqTrace.auth.config;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderRepository;
import com.PascuanSilvestre.TorqTrace.auth.authProvider.enums.EAuthProviders;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProviderInitializer implements CommandLineRunner {
    private final AuthProviderRepository authProviderRepository;

    @Override
    public void run(String... args) {

        createProviderIfNotExists(EAuthProviders.GOOGLE, "Google");

        createProviderIfNotExists(EAuthProviders.GITHUB, "GitHub");

        createProviderIfNotExists(EAuthProviders.FACEBOOK, "Facebook");
    }

    private void createProviderIfNotExists(
            EAuthProviders provider,
            String displayName
    ) {

        if (!authProviderRepository.existsByName(provider)) {
            AuthProviderEntity authProvider =
                    AuthProviderEntity.builder()
                            .name(provider)
                            .displayName(displayName)
                            .build();

            authProviderRepository.save(authProvider);

            System.out.println("Proveedor creado: " + provider.name());

        } else {
            System.out.println("El proveedor ya existe: " + provider.name());
        }
    }
}