//import com.example.guest_flow.repositories.AttendeeRepository;
//import com.example.guest_flow.repositories.EventRepository;
//import domain.event.Event;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class DataSeeder {
//
//    @Bean
//    CommandLineRunner seedDatabase(AttendeeRepository attendeeRepository, EventRepository eventRepository) {
//        return args -> {
//            Event event = new Event();
//            event.setTitle("Unite Summit");
//            event.setSlug("unite-summit");
//            event.setDetails("Um evento p/ devs apaixonados(as) por código!");
//            event.setmaxAttendees(120);
//            eventRepository.save(event);
//
//            List<Attendee> attendees = new ArrayList<>();
//            for (int i = 1; i <= 120; i++) {
//                Attendee attendee = new Attendee();
//                attendee.setName("Attendee " + i);
//                attendee.setEmail("attendee" + i + "@example.com");
//                attendee.setCreatedAt(LocalDateTime.now());
//                attendee.setEvent(event);
//                attendees.add(attendee);
//            }
//            attendeeRepository.saveAll(attendees);
//        };
//    }

