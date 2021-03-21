package com.stef.spring.batch.jpa.repository;

import com.stef.spring.batch.jpa.model.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaysRepository extends JpaRepository< Pays, Long> {
}
