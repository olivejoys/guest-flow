package com.example.guest_flow.services;

import com.example.guest_flow.repositories.AttendeeRepository;
import domain.attendees.Attendees;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    public List<Attendees> getAllAttendeesFromEvent(String eventId) {
       return this.attendeeRepository.findByEventId(eventId);

    }


}
