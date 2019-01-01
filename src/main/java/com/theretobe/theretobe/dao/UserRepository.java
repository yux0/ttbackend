package com.theretobe.theretobe.dao;


import com.theretobe.theretobe.datamodels.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
