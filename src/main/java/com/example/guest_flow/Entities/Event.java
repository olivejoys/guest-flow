package com.example.guest_flow.Entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="events")
@Getter
public class Event {
    @Id
    @Column(nullable=false) // this column cannot be null
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable=false) // this column cannot be null
    private String title;
    @Column (nullable = false)
    private String details;

    @Column(nullable=false, unique = true)
    private String slug;

    @Column(nullable=false, name="max_attendees")
    private Integer max_attendees;


}
