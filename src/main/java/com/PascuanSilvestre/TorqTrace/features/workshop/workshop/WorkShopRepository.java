package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkShopRepository extends JpaRepositoryImplementation<WorkShopEntity,Long> {
    boolean existsById(Long id);
    boolean existsByName(String name);


}
