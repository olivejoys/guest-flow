package com.example.guest_flow.Controllers;


import com.example.guest_flow.dto.dto.event.EventIdDTO;
import com.example.guest_flow.dto.dto.event.EventRequestDTO;
import com.example.guest_flow.dto.dto.event.EventResponseDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeIdDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeRequestDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeesListResponseDTO;
import com.example.guest_flow.services.AttendeeService;
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
    private final EventServices eventServices;
    private final AttendeeService attendeeService;


    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = this.eventServices.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping //we want the status 201 to https status
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventServices.createEvent(body);
        URI uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
        return ResponseEntity.created(uri).body((eventIdDTO));
    }

    @PostMapping("/{id}/attendees") //we want the status 201 to https status
    public ResponseEntity<AttendeeIdDTO> registerParticipant(@PathVariable String id, @RequestBody AttendeeRequestDTO body, UriComponentsBuilder uriComponentsBuilder)  {
        AttendeeIdDTO attendeeIdDTO = this.eventServices.registerAttendeeOnEvent(id,body);
        var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();
        return ResponseEntity.created(uri).body((attendeeIdDTO));
    }


    @GetMapping("/attendees/{id}")
        public ResponseEntity<AttendeesListResponseDTO> getEventAttendees(@PathVariable String id) {
        AttendeesListResponseDTO attendeeListResponse = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeeListResponse);
        }


    }