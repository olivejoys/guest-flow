package com.example.guest_flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"domain.event", "domain.attendees",
		"domain.checkin"}) //added an notation to fix the eventsRepoository
@SpringBootApplication
public class GuestFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestFlowApplication.class, args);

	}

}
