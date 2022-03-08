package com.ruowei.repository;

import com.ruowei.domain.EmiData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the EmiData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmiDataRepository extends JpaRepository<EmiData, Long> {}
