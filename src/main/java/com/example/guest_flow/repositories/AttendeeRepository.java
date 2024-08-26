package com.example.guest_flow.repositories;

import domain.attendees.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendees,String> {
}
