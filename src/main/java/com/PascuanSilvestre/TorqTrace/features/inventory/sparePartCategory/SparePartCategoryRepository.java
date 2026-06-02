package com.PascuanSilvestre.TorqTrace.features.inventory.sparePartCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SparePartCategoryRepository extends JpaRepository<SparePartCategoryEntity, Long> {
    Optional<SparePartCategoryEntity> findByNameIgnoreCaseAndDescriptionIgnoreCase(String name, String description);
}
