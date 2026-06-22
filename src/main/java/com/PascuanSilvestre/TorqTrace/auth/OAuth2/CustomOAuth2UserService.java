package com.PascuanSilvestre.TorqTrace.auth.OAuth2;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderEntity;
import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderRepository;
import com.PascuanSilvestre.TorqTrace.auth.authProvider.enums.EAuthProviders;
import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsEntity;
import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsRepository;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.RoleEntity;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.RoleRepository;
import com.PascuanSilvestre.TorqTrace.auth.permissions.Role.Roles;
import com.PascuanSilvestre.TorqTrace.auth.userProvider.UserProviderEntity;
import com.PascuanSilvestre.TorqTrace.auth.userProvider.UserProviderRepository;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.user.enums.UserStatus;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final UserProviderRepository userProviderRepository;
    private final AuthProviderRepository authProviderRepository;
    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = new DefaultOAuth2UserService().loadUser(userRequest);
        CredentialsEntity credentials = processOAuthUser(oauthUser);
        return new CustomOAuth2User(
                oauthUser.getAuthorities(),
                oauthUser.getAttributes(),
                "sub",
                credentials
        );
    }
    @Transactional
    public CredentialsEntity processOAuthUser(OAuth2User oauthUser) {
        System.out.println("Entrando a processOAuthUser");
        String googleId = oauthUser.getAttribute("sub");
        String email = oauthUser.getAttribute("email");
        String firstName = oauthUser.getAttribute("given_name");
        String lastName = oauthUser.getAttribute("family_name");
        String picture = oauthUser.getAttribute("picture");

        if (email == null || email.isBlank()) {
            throw new RuntimeException("Google account did not provide email");
        }

        AuthProviderEntity googleProvider = authProviderRepository.findByName(EAuthProviders.GOOGLE)
                .orElseThrow(() -> new RuntimeException("Proveedor GOOGLE no encontrado"));

        Optional<UserProviderEntity> providerLink =
                userProviderRepository.findByProviderAndExternalId(googleProvider, googleId);

        if (providerLink.isPresent()) {
            System.out.println("Usuario Google ya vinculado");
            UserEntity user = providerLink.get().getUser();
            System.out.println(user.getId());
            System.out.println(user.getFirstName());

            updateDefaultProfileData(user, firstName, lastName, picture);

            return credentialsRepository.findByUsuario(user)
                    .orElseThrow(() -> new RuntimeException("Credenciales no encontradas"));
        }

        UserEntity user = userRepository.findByUserContactInfoEmail(email)
                .orElseGet(() -> {
                    UserEntity newUser = new UserEntity();
                    newUser.setPublicId(UUID.randomUUID());
                    newUser.setFirstName(firstName);
                    newUser.setLastName(lastName);
                    newUser.setAvatarUrl(picture);
                    newUser.setUserContactInfo(new ContactInfo(null, email));
                    newUser.setStatus(UserStatus.ACTIVE);
                    return userRepository.save(newUser);
                });

        updateDefaultProfileData(user, firstName, lastName, picture);

        UserProviderEntity userProvider = new UserProviderEntity();
        userProvider.setUser(user);
        userProvider.setProvider(googleProvider);
        userProvider.setExternalId(googleId);
        userProviderRepository.save(userProvider);

        Optional<CredentialsEntity> existingCredentials = credentialsRepository.findByUsuario(user);
        if (existingCredentials.isPresent()) {
            return existingCredentials.get();
        }

        RoleEntity userRole = roleRepository.findByRole(Roles.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("ROLE_USER no encontrado"));

        CredentialsEntity credentials = CredentialsEntity.builder()
                .username(email)
                .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                .enabled(true)
                .usuario(user)
                .roles(new HashSet<>(Set.of(userRole)))
                .build();

        return credentialsRepository.save(credentials);
    }

    private void updateDefaultProfileData(UserEntity user, String firstName, String lastName, String picture) {
        boolean updated = false;

        if (user.getFirstName() != null && user.getFirstName().equalsIgnoreCase("Default")) {
            user.setFirstName(firstName);
            updated = true;
        }

        if (user.getLastName() != null && user.getLastName().equalsIgnoreCase("Default")) {
            user.setLastName(lastName);
            updated = true;
        }

        if ((user.getAvatarUrl() == null || user.getAvatarUrl().isBlank()) && picture != null && !picture.isBlank()) {
            user.setAvatarUrl(picture);
            updated = true;
        }

        if (updated) {
            userRepository.save(user);
        }
    }
}
