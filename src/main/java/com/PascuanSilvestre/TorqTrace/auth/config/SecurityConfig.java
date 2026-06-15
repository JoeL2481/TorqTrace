package com.PascuanSilvestre.TorqTrace.auth.config;

import com.PascuanSilvestre.TorqTrace.auth.OAuth2.CustomOAuth2UserService;
import com.PascuanSilvestre.TorqTrace.auth.OAuth2.OAuth2AuthenticationSuccessHandler;
import com.PascuanSilvestre.TorqTrace.auth.filters.JwtAuthenticationFilter;
import com.PascuanSilvestre.TorqTrace.auth.filters.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    //Lo de oauth2
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler successHandler;

    @Bean
    public AuthenticationManager
    authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**",
                                "/oauth2/**",
                                "/login/**").permitAll()
                //ACA VAN LAS RUTAS QUE NO REQUIEREN AUTORIZACION
                     /*   .requestMatchers("/api/vehicles/**").hasRole("USER")*/
                        .anyRequest().authenticated())
                .cors(Customizer.withDefaults())
                //ACA SE CONFIGURAN LOS CORS
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .sessionManagement(manager -> manager.sessionCreationPolicy(IF_REQUIRED))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e -> e.authenticationEntryPoint(restAuthenticationEntryPoint))
                .oauth2Login(oauth -> oauth.userInfoEndpoint(user -> user
                                .userService(customOAuth2UserService))
                                .successHandler(successHandler)
                );
        return http.build();
    }

}

