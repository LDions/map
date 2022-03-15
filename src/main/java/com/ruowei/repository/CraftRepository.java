package com.ruowei.repository;

import com.ruowei.domain.Craft;
import com.ruowei.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the Craft entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CraftRepository extends JpaRepository<Craft, Long>, QuerydslPredicateExecutor<Craft> {

    List<Craft> findByEntCode(String entCode);

    Craft findByCraftCode(String code);
}
