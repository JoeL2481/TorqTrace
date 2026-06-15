package com.PascuanSilvestre.TorqTrace.auth.OAuth2;

import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    private final CredentialsEntity credentials;

    public CustomOAuth2User(
            Collection<? extends GrantedAuthority> authorities,
            Map<String, Object> attributes,
            String nameAttributeKey,
            CredentialsEntity credentials) {

        super(authorities, attributes, nameAttributeKey);

        this.credentials = credentials;
    }
}
