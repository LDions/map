package com.ruowei.repository;

import com.ruowei.domain.SewProcess;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the SewProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewProcessRepository extends JpaRepository<SewProcess, Long> {

    List<SewProcess> findByCraftCode(String craftCode);

    List<SewProcess> findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqual(Instant time1,Instant time2);

    List<SewProcess> findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(Instant time1,Instant time2,String craftCode);

    Optional<SewProcess> findByCraftCodeAndProcessCode(String craftCode, String processCode);

    SewProcess findFirstByOrderByIdDesc();
}
