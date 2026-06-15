package com.PascuanSilvestre.TorqTrace.auth.OAuth2;

import com.PascuanSilvestre.TorqTrace.auth.credentials.CredentialsEntity;
import com.PascuanSilvestre.TorqTrace.auth.jwt.JwtService;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException {

        CustomOAuth2User principal =
                (CustomOAuth2User) authentication.getPrincipal();

        CredentialsEntity credentials = principal.getCredentials();

        UserEntity user = credentials.getUsuario();

        String jwt = jwtService.generateToken(credentials);

        Map<String, Object> body =
                new HashMap<>();

        body.put("token", jwt);

        body.put("publicId",
                user.getPublicId());

        body.put("email",
                user.getUserContactInfo().getEmail());

        body.put("firstName",
                user.getFirstName());

        body.put("lastName",
                user.getLastName());

        response.setStatus(
                HttpServletResponse.SC_OK);

        response.setContentType(
                "application/json");

        objectMapper.writeValue(
                response.getWriter(),
                body
        );
    }
}
