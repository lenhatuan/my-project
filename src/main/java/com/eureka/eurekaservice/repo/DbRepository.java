package com.eureka.eurekaservice.repo;

import com.eureka.eurekaservice.entity.SccoRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbRepository extends JpaRepository<SccoRecord, Integer> {
}
