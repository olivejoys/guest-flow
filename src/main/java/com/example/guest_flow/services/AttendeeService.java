package com.example.guest_flow.services;

import com.example.guest_flow.dto.dto.event.attendee.AttedeeBadgeResponseDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeBadgeDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeDetails;
import com.example.guest_flow.dto.dto.event.attendee.AttendeesListResponseDTO;
import com.example.guest_flow.repositories.AttendeeRepository;
import com.example.guest_flow.repositories.CheckinRepository;
import domain.attendees.Attendees;
import domain.attendees.exceptions.AttendeesNotFoundException;
import domain.checkin.CheckIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendees> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);

    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendees> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList =
                attendeeList.stream().map(attendees -> {
            Optional<CheckIn> checkIn =
                    this.checkinRepository.findByAttendees_Id(attendees.getId());
            LocalDateTime checkedInAt = checkIn
                    .map(checkInObj ->
                            checkInObj.getCreatedAt().atStartOfDay()) // Converts LocalDate to LocalDateTime
                    .orElse(null);
            return new AttendeeDetails(attendees.getId(), attendees.getName(),
                    attendees.getEmail(), attendees.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);

    }

    public Attendees registerAttendee(Attendees newAttendee){
        this.attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    public void verifyAttendeeSubscription(String email, String eventId)
    {
        Optional<Attendees> isAttendeeRegistered =
                this.attendeeRepository.findByEventIdAndEmail(eventId,email);
        if(isAttendeeRegistered.isPresent())
            throw new RuntimeException("Attendee is already registered.");

    }

    public AttedeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        Attendees attendee = this.attendeeRepository.findById
                (attendeeId).orElseThrow(() ->
         new AttendeesNotFoundException("Attendee not found with id" + attendeeId));

        var uri = uriComponentsBuilder.path("/attedees/{attendeeid}/check-in").buildAndExpand(attendeeId).toUri().toString();


        AttendeeBadgeDTO attendeeBadgeDTO = new AttendeeBadgeDTO(attendee.getName(),
                attendee.getEmail(),
                uri, attendee.getEvent().getId());
        return new AttedeeBadgeResponseDTO(attendeeBadgeDTO);

    }

}