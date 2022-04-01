package com.ruowei.repository;

import com.ruowei.domain.SewEmiThreshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SewEmiThresholdRepository extends JpaRepository<SewEmiThreshold, Long> {

    Optional<SewEmiThreshold> findByEnterpriseCode(String enterpriseCode);
}
