package domain.attendees.exceptions;

public class AttendeesNotFoundException extends RuntimeException{
    public AttendeesNotFoundException(String message){
        super(message);
    }
}
