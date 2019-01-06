package com.theretobe.theretobe.controller;

import com.google.gson.Gson;
import com.theretobe.theretobe.Constants;
import com.theretobe.theretobe.dao.TripRepository;
import com.theretobe.theretobe.datamodels.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Controller
@RequestMapping(path = "/trip")
public class TripController {

    @Autowired
    private TripRepository repository;

    private Gson gson = new Gson();

    @GetMapping(path = "/{id}")
    @ResponseBody
    public HttpEntity<Trip> get(@PathVariable("id") Long tripId) {
        return ResponseEntity.ok(repository.findById(tripId).get());
    }

    @GetMapping(path = "/list/{filter}/{data}/{page}")
    @ResponseBody
    public HttpEntity<Page<Trip>> list(@PathVariable("filter") String filter, String data, int page) throws BadRequest {
        Page<Trip> trips;

        switch (filter) {
            case Constants.TRIP_FILTER_DEST:
                Pageable locPage = PageRequest.of(page, Constants.DEFAULT_PAGE_SIZE);
                trips = repository.findByStartLocation(data, locPage);
                break;
            case Constants.TRIP_FILTER_DATE:
                Pageable timePage = PageRequest.of(page, Constants.DEFAULT_PAGE_SIZE, Sort.Direction.ASC, "startTime");
                LocalDateTime localTime = LocalDateTime.parse(data);

                trips = repository.findByStartTime(OffsetDateTime.of(localTime, ZoneOffset.UTC), timePage);
                break;
            default:
                return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(trips);
    }

    @PutMapping
    @ResponseBody
    public HttpEntity<Trip> create(@RequestBody String body) {
        Trip trip = gson.fromJson(body, Trip.class);
        repository.save(trip);
        return ResponseEntity.ok(trip);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HttpEntity delete(@PathVariable("id") Long tripId) {
        repository.deleteById(tripId);
        return ResponseEntity.accepted().build();
    }
}
