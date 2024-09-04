package com.example.guest_flow.repositories;

import domain.attendees.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendees,String> {
    List<Attendees> findByEventId(String eventId);

    Optional<Attendees> findByEventIdAndEmail(String eventId, String email);
}
