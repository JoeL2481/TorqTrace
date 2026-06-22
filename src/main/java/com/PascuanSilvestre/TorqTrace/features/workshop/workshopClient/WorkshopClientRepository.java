package com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient;

import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkshopClientRepository
        extends JpaRepository<WorkshopClientEntity, Long>, JpaSpecificationExecutor<WorkshopClientEntity> {


    List<WorkshopClientEntity> findByWorkshop(Long workShopId);


    Optional<WorkshopClientEntity> findByUserId(Long userId);

    Optional<WorkshopClientEntity> findByUserIdAndWorkshopId(Long userId, Long workshopId);

    boolean existsByUserIdAndWorkshopId(Long userId, Long workshopId);
}
