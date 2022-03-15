package com.ruowei.repository;

import com.ruowei.domain.EntCraftData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface EntCraftProcessRepository extends JpaRepository<EntCraftData, Long>, QuerydslPredicateExecutor<EntCraftData> {

    List<EntCraftData> findByEntCodeAndCraftCodeAndDayTimeAndMessageSource(String entCode,String craftCode,Instant daytime,String leixing);

}
