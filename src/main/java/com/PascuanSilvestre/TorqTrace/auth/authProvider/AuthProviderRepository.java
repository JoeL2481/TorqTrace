package com.PascuanSilvestre.TorqTrace.auth.authProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthProviderRepository extends JpaRepository<AuthProviderEntity, Long> {

}
