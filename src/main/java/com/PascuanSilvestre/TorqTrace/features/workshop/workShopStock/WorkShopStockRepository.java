package com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkShopStockRepository extends JpaRepository<WorkShopStockEntity,Long> {
    List<WorkShopStockEntity>
    findByWorkshopId(Long workshopId);
}
