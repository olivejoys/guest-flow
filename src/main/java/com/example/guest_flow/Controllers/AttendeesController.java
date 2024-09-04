package com.example.guest_flow.Controllers;

import com.example.guest_flow.dto.dto.event.attendee.AttendeeBadgeResponseDTO;
import com.example.guest_flow.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendees")
public class AttendeesController {
    private final AttendeeService attendeeService;
    @GetMapping("/{attendeeId}/badge")//added GetMapinng and fixed the /events/events error
    public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeBadgeResponseDTO response = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{attendeeId}/check-in") ///created the checkin registered
    public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        this.attendeeService.checkInAttendee(attendeeId);
        var uri = uriComponentsBuilder.path("/attendees/{attendeesId}/badge").buildAndExpand(attendeeId).toUri();
        return ResponseEntity.created(uri).build();

    }

}
