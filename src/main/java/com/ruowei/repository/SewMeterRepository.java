package com.ruowei.repository;

import com.ruowei.domain.SewMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the SewMeter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewMeterRepository extends JpaRepository<SewMeter,Long>{

    List<SewMeter> findByDocumentCode(String documentCode);
}
