package com.stef.spring.batch.jpa.repository;

import com.stef.spring.batch.jpa.model.PaysFrancais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysFrancaisRepository extends JpaRepository< PaysFrancais, Long> {
}
