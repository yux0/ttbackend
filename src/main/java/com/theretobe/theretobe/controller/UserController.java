package com.theretobe.theretobe.controller;

import com.google.gson.Gson;
import com.theretobe.theretobe.dao.UserRepository;
import com.theretobe.theretobe.datamodels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import static com.theretobe.theretobe.Constants.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    private Gson gson = new Gson();

    @GetMapping(path = "/{id}")
    @ResponseBody
    public HttpEntity<User> get(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(repository.findById(userId).get());
    }

    @PutMapping
    @ResponseBody
    public HttpEntity<User> create(@RequestBody String body) {
        User user = gson.fromJson(body, User.class);

        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/place")
    @ResponseBody
    public HttpEntity<User> addFootprint(@PathVariable("id") Long userId) {
        User user = repository.getOne(userId);
        final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        final String newSouvenir = (String) attributes.getAttribute(USER_FOOTPRINT, RequestAttributes.SCOPE_REQUEST);
        String souvenir = user.getFootprint();
        souvenir += "," + newSouvenir;
        user.setFootprint(souvenir);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity delete(@PathVariable("id") Long userId) {
        repository.deleteById(userId);
        return ResponseEntity.accepted().build();
    }
}
