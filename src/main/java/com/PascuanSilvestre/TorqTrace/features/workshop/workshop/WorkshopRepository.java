package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends JpaRepositoryImplementation<WorkshopEntity,Long> {
}
