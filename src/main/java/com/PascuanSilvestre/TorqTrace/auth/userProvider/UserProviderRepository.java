package com.PascuanSilvestre.TorqTrace.auth.userProvider;

import com.PascuanSilvestre.TorqTrace.auth.authProvider.AuthProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProviderRepository extends JpaRepository<UserProviderEntity, Long> {

    //Optional<UserProviderEntity> findByProviderAndExternalId(AuthProviderEntity provider, String externalId);
    @Query("""
    SELECT up
    FROM UserProviderEntity up
    JOIN FETCH up.user
    WHERE up.provider = :provider
      AND up.externalId = :externalId
""")
    Optional<UserProviderEntity> findByProviderAndExternalId(
            @Param("provider") AuthProviderEntity provider,
            @Param("externalId") String externalId
    );
}
