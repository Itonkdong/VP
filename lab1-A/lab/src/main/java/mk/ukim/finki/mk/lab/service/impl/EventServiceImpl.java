package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.helper.CustomHandler;
import mk.ukim.finki.mk.lab.repository.InMemoryEventRepository;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService
{

    private final InMemoryEventRepository repository;

    public EventServiceImpl(InMemoryEventRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public List<Event> listAll()
    {
        return repository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text)
    {
        return repository.searchEvents(text);
    }

    @Override
    public List<Event> filterEvents(String text, String rating)
    {
        Optional<String> textOptional = CustomHandler.createOptional(text);
        Optional<String> ratingOptional = CustomHandler.createOptional(rating);

        //Not needed because the bottom code handles these situations, but this is faster
        if (textOptional.isEmpty() && ratingOptional.isEmpty()) return this.listAll();

        return this.listAll()
                .stream()
                .filter(event -> CustomHandler
                        .filterHandle(
                                ratingOptional,
                                event,
                                event1->event1.getPopularityScore() >= Double.parseDouble(ratingOptional.get())))
                .filter(event -> CustomHandler
                        .filterHandle(
                                textOptional,
                                event,
                                event1 -> event1.getName().contains(textOptional.get())))
                .toList();

    }
}
