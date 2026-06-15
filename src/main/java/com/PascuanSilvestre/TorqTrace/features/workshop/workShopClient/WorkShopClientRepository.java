package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkShopClientRepository
        extends JpaRepository<WorkShopClientEntity, Long>, JpaSpecificationExecutor<WorkShopClientEntity> {

    // Buscar relación cliente-taller por user
    List<WorkShopClientEntity> findByUser(UserEntity user);

    // Buscar todos los clientes de un taller
    List<WorkShopClientEntity> findByWorkshop(Long workShopId);

    // Buscar relación específica user + workshop
    Optional<WorkShopClientEntity> findByUserAndWorkshop(UserEntity user, WorkShopEntity workshop);

    // Variante por IDs
    Optional<WorkShopClientEntity> findByUserId(Long userId);

    List<WorkShopClientEntity> findByWorkshopId(Long workshopId);

    boolean existsByUserIdAndWorkshopId(Long userId, Long workshopId);
}
