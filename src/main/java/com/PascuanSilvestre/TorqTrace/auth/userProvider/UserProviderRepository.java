package com.PascuanSilvestre.TorqTrace.auth.userProvider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProviderRepository extends JpaRepository<UserProviderEntity, Long> {
}
