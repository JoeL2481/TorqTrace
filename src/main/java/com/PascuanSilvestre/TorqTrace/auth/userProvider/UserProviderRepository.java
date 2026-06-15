package com.PascuanSilvestre.TorqTrace.auth.userProvider;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProviderRepository extends JpaRepository<UserProviderEntity, Long> {

    Optional<UserProviderEntity> findByProviderAndExternalId(AuthProviderEntity provider, String externalId);
}
