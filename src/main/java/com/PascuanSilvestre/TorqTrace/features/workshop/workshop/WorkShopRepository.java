package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkShopRepository extends JpaRepositoryImplementation<WorkShopEntity,Long> {
    boolean existsByName(String name);
}
