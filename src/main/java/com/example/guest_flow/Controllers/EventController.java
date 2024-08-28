package com.example.guest_flow.Controllers;


import com.example.guest_flow.dto.dto.event.EventIdDTO;
import com.example.guest_flow.dto.dto.event.EventRequestDTO;
import com.example.guest_flow.dto.dto.event.EventResponseDTO;
import com.example.guest_flow.services.EventServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventServices serv;


    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = this.serv.getEventDetail(id);
        return ResponseEntity.ok(event);
        }

        @PostMapping //we want the status 201 to https status
        public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
           EventIdDTO eventIdDTO = this.serv.createEvent(body);
            URI uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
            return ResponseEntity.created(uri).body((eventIdDTO));
        }
    }