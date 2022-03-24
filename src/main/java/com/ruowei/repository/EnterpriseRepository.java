package com.ruowei.repository;

import com.ruowei.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Enterprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>, QuerydslPredicateExecutor<Enterprise> {

    List<Enterprise> findByGroupCode(String groupCode);

    Optional<Enterprise> findByCode(String code);

    Optional<Enterprise> findByCodeAndIsTryIsTrue(String code);

    Optional<Enterprise> findByCodeAndGroupCodeAndIsTry(String code, String groupCode, Boolean tryed);

    void deleteByCode(String code);

}
