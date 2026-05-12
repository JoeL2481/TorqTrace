package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkShopRepository extends JpaRepositoryImplementation<WorkShopEntity,Long> {
    boolean existsByName(String name);

    @Query("""
    SELECT wo
    FROM WorkOrderEntity wo
    LEFT JOIN FETCH wo.workshop
    LEFT JOIN FETCH wo.client
    LEFT JOIN FETCH wo.vehicle
    WHERE wo.id = :id
""")
    Optional<WorkShopEntity> findDetailedById(Long id);

}
