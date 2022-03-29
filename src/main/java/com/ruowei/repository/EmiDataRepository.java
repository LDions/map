package com.ruowei.repository;

import com.ruowei.domain.EmiData;
import com.ruowei.domain.SewProcess;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

/**
 * Spring Data SQL repository for the EmiData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmiDataRepository extends JpaRepository<EmiData, Long> {


    List<EmiData> findByPredictTimeIsGreaterThanEqualAndPredictTimeIsLessThanEqualAndCraftCode(Instant time1,Instant time2,String craftCode);

}
