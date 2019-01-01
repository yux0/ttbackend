package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.Follow;
import com.theretobe.theretobe.datamodels.TripRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRequestRepository extends JpaRepository<TripRequest, Long> {
}
