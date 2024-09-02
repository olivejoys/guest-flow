package com.example.guest_flow.repositories;

import domain.attendees.Attendees;
import domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Attendees, Integer> {
Optional<CheckIn> findByAttendeeId(String attendeeId);

}

