package com.example.guest_flow.services;

import com.example.guest_flow.dto.dto.event.EventIdDTO;
import com.example.guest_flow.dto.dto.event.EventRequestDTO;
import com.example.guest_flow.dto.dto.event.EventResponseDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeIdDTO;
import com.example.guest_flow.dto.dto.event.attendee.AttendeeRequestDTO;
import com.example.guest_flow.repositories.EventRepository;
import domain.attendees.Attendees;
import domain.attendees.exceptions.EventFullExeption;
import domain.event.Event;
import domain.exceptions.EventNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class EventServices {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.getEventById(eventId); // it will be optional
        List<Attendees> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());

    }

    public EventIdDTO createEvent(EventRequestDTO eventTDO) {
        Event newEvent = new Event();
        newEvent.setTitle(eventTDO.title());
        newEvent.setDetails(eventTDO.details());
        newEvent.setMaxAttendees(eventTDO.maxAttendees());
        newEvent.setSlug(this.createSlug(eventTDO.title()));

        this.eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());


    }

    public AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO attendeeRequestDTO) { //verifying if the guest exists already
        this.attendeeService.verifyAttendeeSubscription(attendeeRequestDTO.email(), eventId);

        Event event = this.getEventById(eventId);
        List<Attendees> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        if (event.getMaxAttendees() <= attendeeList.size())
            throw new EventFullExeption("Event is full.");
        { //verify if the event is full

            Attendees newAttendee = new Attendees(); //creating the attendee now
            newAttendee.setName(attendeeRequestDTO.name());
            newAttendee.setEmail(attendeeRequestDTO.email());
            newAttendee.setEvent(event);
            newAttendee.setCreatedAt(LocalDateTime.now());
            this.attendeeService.registerAttendee(newAttendee); // it can be only the id

            return new AttendeeIdDTO(newAttendee.getId());

        }
    }

        private Event getEventById(String eventId){
          return this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found with ID:" + eventId));

        }


        private String createSlug (String text){
            String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
            return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]",
                            "")
                    .replaceAll("[^\\w\\s]", "")
                    .replaceAll("[s+]", "-")
                    .toLowerCase();

        }
    }
