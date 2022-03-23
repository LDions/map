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

    List<Enterprise> findByGroupId(Long groupId);
    Optional<Enterprise> findByCode(String code);

    Optional<Enterprise> findByCodeAndIsTryIsTrue(String code);

    Optional<Enterprise> findByCodeAndGroupIdAndIsTryIsTrue(String code,Long groupId);

    void deleteByCode(String code);

    Optional<Enterprise> findByCodeAndGroupIdAndIsTry(String code, Long groupId, Boolean tryed);
}
