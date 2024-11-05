package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService
{
    List<Event> listAll();
    List<Event> searchEvents(String text);
    List<Event> filterEvents(String text, String rating);
}
