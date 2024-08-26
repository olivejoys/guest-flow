package com.example.guest_flow.dto.dto.event;

public record EventDetailDTO(
        String id,
        String title,
        String details,
        String slug,
        Integer max_attendees,
        Integer attendeesAmount
) {

}
