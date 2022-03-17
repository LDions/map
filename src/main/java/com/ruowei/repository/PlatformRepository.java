package com.ruowei.repository;

import com.ruowei.domain.Enterprise;
import com.ruowei.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Enterprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long>, QuerydslPredicateExecutor<Platform> {

}
