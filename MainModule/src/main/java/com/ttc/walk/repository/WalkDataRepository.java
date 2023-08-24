package com.ttc.walk.repository;

import com.ttc.walk.model.entity.WalkDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalkDataRepository extends JpaRepository<WalkDataEntity, String> {

}
