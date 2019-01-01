package com.theretobe.theretobe.controller;

import com.theretobe.theretobe.dao.UserRepository;
import com.theretobe.theretobe.datamodels.User;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Long userId) {
        return repository.getOne(userId);
    }

    @PutMapping("/create")
    @ResponseBody
    public ResponseEntity<User> create() {
        final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        final Long userId = (Long) attributes.getAttribute(USER_ID, RequestAttributes.SCOPE_REQUEST);
        final String firstName = (String) attributes.getAttribute(USER_FIRSTNAME, RequestAttributes.SCOPE_REQUEST);
        final String middleName = (String) attributes.getAttribute(USER_MIDDLENAME, RequestAttributes.SCOPE_REQUEST);
        final String lastName = (String) attributes.getAttribute(USER_LASTNAME, RequestAttributes.SCOPE_REQUEST);
        final String email = (String) attributes.getAttribute(USER_EMAIL, RequestAttributes.SCOPE_REQUEST);
        final String phone = (String) attributes.getAttribute(USER_PHONE, RequestAttributes.SCOPE_REQUEST);
        User user;
        if (userId != null && repository.existsById(userId)) {
            user = repository.getOne(userId);
        } else {
            user = new User();
        }
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        repository.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/place")
    @ResponseBody
    public ResponseEntity<User> create(@PathVariable("id") Long userId) {
        User user = repository.getOne(userId);
        final RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        final String newSouvenir = (String) attributes.getAttribute(USER_SOUVENIR, RequestAttributes.SCOPE_REQUEST);
        String souvenir = user.getSouvenir();
        souvenir += "," + newSouvenir;
        user.setSouvenir(souvenir);
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
