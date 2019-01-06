package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface TripRepository extends JpaRepository<Trip, Long> {
    Page<Trip> findByStartLocation(String startLocation, Pageable pageable);

    @Query("select t from trip t where t.startTime > ?1")
    Page<Trip> findByStartTime(OffsetDateTime startTime, Pageable pageable);
}
