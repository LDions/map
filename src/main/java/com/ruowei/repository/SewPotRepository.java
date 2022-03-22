package com.ruowei.repository;

import com.ruowei.domain.SewPot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the SewPot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SewPotRepository extends JpaRepository<SewPot, Long> {

    /**
     * 根据工艺ID查询日报数据
     * @param craftId 工艺ID
     * @return
     */
    List<SewPot> findByCraftId(@NotNull Long craftId);

    Optional<SewPot> findByCraftCodeAndPotCode(String craftCode, String potCode);
}
