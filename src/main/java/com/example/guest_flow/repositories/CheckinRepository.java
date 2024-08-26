package com.example.guest_flow.repositories;

import domain.attendees.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<Attendees, Integer> {
}
