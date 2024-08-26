package com.example.guest_flow.Controllers;


import com.example.guest_flow.services.EventServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventServices serv;


    @GetMapping("/{id}")
    public ResponseEntity<String> getEvent(@PathVariable String id) {
        this.serv.getEventDetail(id);
        return ResponseEntity.ok("success");


        }

    }