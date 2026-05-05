package com.PascuanSilvestre.TorqTrace.features.workshop.client;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkshopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository
        extends JpaRepository<ClientEntity, Long>, JpaSpecificationExecutor<ClientEntity> {

    // Buscar relación cliente-taller por user
    List<ClientEntity> findByUser(UserEntity user);

    // Buscar todos los clientes de un taller
    List<ClientEntity> findByWorkshop(WorkshopEntity workshop);

    // Buscar relación específica user + workshop
    Optional<ClientEntity> findByUserAndWorkshop(UserEntity user, WorkshopEntity workshop);

    // Variante por IDs
    List<ClientEntity> findByUserId(Long userId);

    List<ClientEntity> findByWorkshopId(Long workshopId);

    Optional<ClientEntity> findByUserIdAndWorkshopId(Long userId, Long workshopId);
}
