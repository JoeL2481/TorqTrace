package com.PascuanSilvestre.TorqTrace.auth.authProvider;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.enums.EAuthProviders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthProviderRepository extends JpaRepository<AuthProviderEntity, Long> {

    Optional<AuthProviderEntity> findByName(EAuthProviders name);
    boolean existsByName(EAuthProviders name);
}
