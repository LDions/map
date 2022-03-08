package com.ruowei.repository;

import com.ruowei.domain.EmiFactor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Spring Data SQL repository for the EmiFactor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmiFactorRepository extends JpaRepository<EmiFactor, Long> {


    Optional<EmiFactor> findFirstByProjectCodeOrderByModifyDateDesc(@NotNull String projectCode);

    boolean existsByProjectCodeAndModifyDate(@NotNull String projectCode, @NotNull LocalDate modifyDate);
}
