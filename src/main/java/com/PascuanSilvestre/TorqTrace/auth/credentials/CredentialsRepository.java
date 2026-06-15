package com.PascuanSilvestre.TorqTrace.auth.credentials;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends JpaRepository<CredentialsEntity,Long> {

    Optional<CredentialsEntity> findByUsername(String username);

    Optional<CredentialsEntity> findByUsuario(UserEntity user);
}
