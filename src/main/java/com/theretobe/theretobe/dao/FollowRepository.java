package com.theretobe.theretobe.dao;

import com.theretobe.theretobe.datamodels.Follow;
import com.theretobe.theretobe.datamodels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Follow findByFolloweeAndFollower(Long followee, Long follower);

    List<Follow> findByFollowee(Long followee);

    List<Follow> findByFollower(Long follower);

    @Modifying
    @Query("delete from follow u where followee = ?1 and follower = ?2")
    void delete(Long followee, Long follower);
}

