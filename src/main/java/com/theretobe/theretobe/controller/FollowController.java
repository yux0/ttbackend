package com.theretobe.theretobe.controller;

import com.theretobe.theretobe.dao.FollowRepository;
import com.theretobe.theretobe.dao.UserRepository;
import com.theretobe.theretobe.datamodels.Follow;
import com.theretobe.theretobe.datamodels.User;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/follow")
public class FollowController {
    @Autowired
    private FollowRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/followee/{id}")
    @ResponseBody
    public List<Follow> findByFollowee(@PathVariable("id") Long userId) {
        return repository.findByFollowee(userId);
    }

    @GetMapping(path = "follower/{id}")
    @ResponseBody
    public List<Follow> findByFollower(@PathVariable("id") Long userId) {
        return repository.findByFollower(userId);
    }

    @GetMapping(path = "{followeeID}/{followerID}")
    @ResponseBody
    public Follow find(@PathVariable("followeeID") Long followeeID,@PathVariable("followerID") Long followerID) {
        return repository.findByFolloweeAndFollower(followeeID, followerID);
    }

    @PutMapping("{followeeID}/{followerID}")
    @ResponseBody
    public ResponseEntity<Follow> create(@PathVariable("followeeID") Long followeeID,@PathVariable("followerID") Long followerID) {
        Follow follow = new Follow();
        User followee = userRepository.getOne(followeeID);
        User follower = userRepository.getOne(followerID);
        follow.setFollowee(followee);
        follow.setFollower(follower);
        repository.save(follow);
        return ResponseEntity.ok(follow);
    }

    @DeleteMapping(path = "{followeeID}/{followerID}")
    @ResponseBody
    public int delete(@PathVariable("followeeID") Long followeeID,@PathVariable("followerID") Long followerID) {
        repository.delete(followeeID, followerID);
        return Response.SC_ACCEPTED;
    }
}
