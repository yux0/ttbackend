package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
