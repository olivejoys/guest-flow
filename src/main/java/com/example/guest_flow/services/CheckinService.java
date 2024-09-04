package com.example.guest_flow.services;

import com.example.guest_flow.repositories.CheckinRepository;
import domain.attendees.Attendees;
import domain.checkin.CheckIn;
import domain.checkin.exception.CheckInAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CheckinService {
    private final CheckinRepository checkinRepository;

    public void registerCheckIn(Attendees attendee){
        this.verifyCheckInExists(attendee.getId());
        CheckIn newCheckIn = new CheckIn();
        newCheckIn.setAttendees(attendee);
        newCheckIn.setCreatedAt(LocalDate.from(LocalDateTime.now()));

        this.checkinRepository.save(newCheckIn);
    }

    private void verifyCheckInExists(String attendeeId){
        Optional<CheckIn> isCheckedIn =
                this.getCheckIn(attendeeId);
        if(isCheckedIn.isPresent())throw new
                CheckInAlreadyExistsException("Attendee is already checked.");

    }


    public Optional<CheckIn> getCheckIn(String attendeeId){
       return this.checkinRepository.findByAttendees_Id(attendeeId);
    }
}

