package com.example.guest_flow.services;

import com.example.guest_flow.dto.dto.event.attendee.AttendeeBadgeDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeBadgeResponseDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeDetails;
import com.example.guest_flow.dto.dto.event.attendee.AttendeesListResponseDTO;
import com.example.guest_flow.repositories.AttendeeRepository;
import domain.attendees.Attendees;
import domain.attendees.exceptions.AttendeesNotFoundException;
import domain.checkin.CheckIn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinService checkinService;

    public List<Attendees> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);

    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId, int page, int size) {
        // Set default values if page or size are null
        int defaultPage = ( page < 0) ? 0 : page;
        int defaultSize = (size <= 0) ? 10 : size;

        List<Attendees> attendeeList = this.getAllAttendeesFromEvent(eventId);
        Pageable pageable = PageRequest.of(defaultPage, defaultSize);
        int start = Math.min((int) pageable.getOffset(), attendeeList.size()-1);
        int end = Math.min((start + pageable.getPageSize()), attendeeList.size());

        // Slice the list to create a paginated sublist

        List<Attendees> paginatedAttendees = attendeeList.subList(start, end);

        List<AttendeeDetails> attendeeDetailsList = paginatedAttendees.stream().map(attendees -> {
            Optional<CheckIn> checkIn = this.checkinService.getCheckIn(attendees.getId());
            LocalDateTime checkedInAt = checkIn
                    .map(checkInObj -> checkInObj.getCreatedAt().atStartOfDay())
                    .orElse(null);
            return new AttendeeDetails(attendees.getId(), attendees.getName(),
                    attendees.getEmail(), attendees.getCreatedAt(), checkedInAt);
        }).toList();


        return new AttendeesListResponseDTO(attendeeDetailsList, attendeeList.size(), page, size);

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

    public void checkInAttendee(String attendeeId){
        Attendees attendee = this.getAttendees(attendeeId);
        this.checkinService.registerCheckIn(attendee);
    }

    private Attendees getAttendees(String attendeeId){
        return this.attendeeRepository.findById
                (attendeeId).orElseThrow(() ->
                new AttendeesNotFoundException("Attendee not found with id" +
                        attendeeId));
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId,
           UriComponentsBuilder uriComponentsBuilder){
        Attendees attendee = this.getAttendees(attendeeId);

        var uri = uriComponentsBuilder.path("/attedees/{attendeeid}/check-in")
                .buildAndExpand(attendeeId).
                toUri().toString();


        AttendeeBadgeDTO attendeeBadgeDTO = new AttendeeBadgeDTO(attendee.getName(),
                attendee.getEmail(),
                uri, attendee.getEvent().getId());
        return new AttendeeBadgeResponseDTO(attendeeBadgeDTO);

    }

}