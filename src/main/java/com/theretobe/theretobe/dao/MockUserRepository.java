package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.MockUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MockUserRepository extends JpaRepository<MockUser, Long> {
}
