package com.ruowei.repository;

import com.ruowei.domain.Geometries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeometriesRepository extends JpaRepository<Geometries, Long>, QuerydslPredicateExecutor<Geometries> {

//    @Query(value = "SELECT * FROM geometries", nativeQuery = true)
    List<Geometries> findAllByLocationId(Long locationId);
}
