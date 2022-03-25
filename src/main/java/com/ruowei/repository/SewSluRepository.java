package com.ruowei.repository;

import com.ruowei.domain.SewSlu;
import liquibase.pro.packaged.L;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the SewSlu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewSluRepository extends JpaRepository<SewSlu, Long> {

    List<SewSlu> findByCraftCode(String craftCode);

    List<SewSlu> findByDayTimeIsGreaterThanEqualAndDayTimeIsLessThanEqualAndCraftCode(Instant time1,Instant time2,String craftCode);

    Optional<SewSlu> findByCraftCodeAndSluCode(String craftCode, String sluCode);
}
