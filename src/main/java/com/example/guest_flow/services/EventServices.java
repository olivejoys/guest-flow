package com.example.guest_flow.services;

import com.example.guest_flow.repositories.EventRepository;
import domain.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EventServices {
    private final EventRepository eventRepository;

    public void getEventDetail(String eventId){
       Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID.")); // it will be optional

        return;

    }

}
