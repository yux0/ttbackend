package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}

