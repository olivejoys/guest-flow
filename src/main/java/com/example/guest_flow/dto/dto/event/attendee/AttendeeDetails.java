package com.example.guest_flow.dto.dto.event.attendee;

import java.time.LocalDateTime;

public record AttendeeDetails(String id, String name, String email,
                              LocalDateTime createdAt, LocalDateTime checkedInAt) {
}
