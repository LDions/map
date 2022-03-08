package com.ruowei.repository;

import com.ruowei.domain.Enterprise;
import com.ruowei.domain.enumeration.EnterpriseStatusType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Enterprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>, QuerydslPredicateExecutor<Enterprise> {

    Optional<Enterprise> getFirstByUniCreditCodeAndStatus(@NotNull String uniCreditCode, @NotNull EnterpriseStatusType status);

    Optional<Enterprise> getFirstByUniCreditCodeAndIdNotAndStatus(@NotNull String uniCreditCode, Long id, @NotNull EnterpriseStatusType status);

    Optional<Enterprise> findByIdAndStatus(@NotNull Long id, @NotNull EnterpriseStatusType statusType);

    List<Enterprise> findAllByStatus(@NotNull EnterpriseStatusType status);
}
