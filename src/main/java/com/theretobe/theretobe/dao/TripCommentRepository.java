package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.Trip;
import com.theretobe.theretobe.datamodels.TripComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripCommentRepository extends JpaRepository<TripComment, Long> {
}
