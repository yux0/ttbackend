package com.theretobe.theretobe.controller;

import com.theretobe.theretobe.dao.MockUserRepository;
import com.theretobe.theretobe.datamodels.MockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/fb_user")
public class MockUserController {
    @Autowired
    private MockUserRepository repository;

    @PutMapping
    @ResponseBody
    public HttpEntity<MockUser> create(@RequestBody MockUser user) {
        System.out.println("email:::"+user.getEmail());
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public HttpEntity<MockUser> get(@PathVariable("id") Long userId) {
        MockUser u = repository.getOne(userId);
        return ResponseEntity.ok(u);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity delete(@PathVariable("id") Long userId) {
        repository.deleteById(userId);
        return ResponseEntity.accepted().build();
    }
}
