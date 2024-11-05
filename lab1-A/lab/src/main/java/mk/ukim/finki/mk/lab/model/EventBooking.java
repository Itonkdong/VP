package mk.ukim.finki.mk.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
@Getter
@Setter
public class EventBooking
{
    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;
}
