package com.ruowei.repository;

import com.ruowei.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>, QuerydslPredicateExecutor<Location> {
}
