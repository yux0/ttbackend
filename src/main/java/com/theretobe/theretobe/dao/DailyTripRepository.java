package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.DailyTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyTripRepository extends JpaRepository<DailyTrip, Long> {
}
