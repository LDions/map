package com.ruowei.repository;

import com.ruowei.domain.SewPot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the SewPot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewPotRepository extends JpaRepository<SewPot, Long> {

    List<SewPot> findByCraftCode(String craftCode);

    List<SewPot> findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(Instant time1, Instant time2, String CraftCode);

    Optional<SewPot> findByCraftCodeAndPotCode(String craftCode, String potCode);
}
