package com.theretobe.theretobe.controller;

import com.google.gson.Gson;
import com.theretobe.theretobe.dao.UserRepository;
import com.theretobe.theretobe.datamodels.User;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.support.RequestContext;

import static com.theretobe.theretobe.Constants.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    private Gson gson = new Gson();

    @GetMapping(path = "/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long userId) {
        return repository.findById(userId).get();
    }

    @PutMapping("/create")
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody String body) {
        User user = gson.fromJson(body, User.class);

        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/place")
    @ResponseBody
    public ResponseEntity<User> create(@PathVariable("id") Long userId) {
        User user = repository.getOne(userId);
        final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        final String newSouvenir = (String) attributes.getAttribute(USER_SOUVENIR, RequestAttributes.SCOPE_REQUEST);
        String souvenir = user.getFootprint();
        souvenir += "," + newSouvenir;
        user.setFootprint(souvenir);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public int delete(@PathVariable("id") Long userId) {
        repository.deleteById(userId);
        return Response.SC_ACCEPTED;
    }
}
