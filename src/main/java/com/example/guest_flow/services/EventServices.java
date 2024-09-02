package com.example.guest_flow.services;

import com.example.guest_flow.dto.dto.event.EventIdDTO;
import com.example.guest_flow.dto.dto.event.EventRequestDTO;
import com.example.guest_flow.dto.dto.event.EventResponseDTO;
import com.example.guest_flow.repositories.EventRepository;
import domain.attendees.Attendees;
import domain.event.Event;
import domain.exceptions.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor

public class EventServices {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId){
       Event event = this.eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException("Event not found with ID:" + eventId)); // it will be optional
        List<Attendees> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());

    }

    public EventIdDTO createEvent(EventRequestDTO eventTDO){
        Event newEvent = new Event();
        newEvent.setTitle(eventTDO.title());
        newEvent.setDetails(eventTDO.details());
        newEvent.setMaxAttendees(eventTDO.maxAttendees());
        newEvent.setSlug(this.createSlug(eventTDO.title()));

        this.eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());



    }

    private String createSlug(String text){
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]",
                        "")
                .replaceAll("[^\\w\\s]", "")
                .replaceAll("[s+]", "-")
                .toLowerCase();

    }
}
