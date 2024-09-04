package domain.attendees.exceptions;

public class EventFullExeption extends RuntimeException{
    public EventFullExeption(String message) {
        super(message);
    }
}
