package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryEventRepository
{
    public List<Event> findAll()
    {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text)
    {
        return DataHolder
                .events
                .stream()
                .filter(e->e.getName().contains(text) || e.getDescription().contains(text))
                .toList();
    }
}
