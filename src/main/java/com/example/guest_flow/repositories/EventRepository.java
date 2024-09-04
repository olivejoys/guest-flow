package com.example.guest_flow.repositories;

import domain.attendees.Attendees;
import domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,String> {

}