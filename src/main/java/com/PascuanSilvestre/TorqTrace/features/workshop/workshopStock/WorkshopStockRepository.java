package com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkshopStockRepository extends JpaRepository<WorkshopStockEntity,Long> {
    List<WorkshopStockEntity> findByWorkshopId(Long workshopId);
}
