package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SparePartCategoryRepository extends JpaRepository<SparePartCategoryEntity, Long> {
}
