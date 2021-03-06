package com.waveaccess.waveaccesstesttask.controller;

import com.waveaccess.waveaccesstesttask.model.Schedule;
import com.waveaccess.waveaccesstesttask.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import static com.waveaccess.waveaccesstesttask.security.SecurityUtil.authUserId;
import static com.waveaccess.waveaccesstesttask.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = ScheduleRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ScheduleRestController {
    static final String REST_URL = "/rest/schedules";

    @Autowired
    private ScheduleService service;

    @GetMapping("/all")
    public List<Schedule> getAll() {
        return service.getAll();
    }

    @GetMapping
    public List<Schedule> getAllWithAuth() {
        return service.getAll(authUserId());
    }

    @GetMapping("/{id}")
    public Schedule get(@PathVariable int id) {
        return service.get(id, authUserId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Schedule> createWithLocation(@Valid @RequestBody Schedule schedule) {
        Schedule created = service.create(schedule, authUserId());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id, authUserId());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Schedule schedule, @PathVariable int id) {
        assureIdConsistent(schedule, id);
        service.update(schedule, id, authUserId());
    }
}
