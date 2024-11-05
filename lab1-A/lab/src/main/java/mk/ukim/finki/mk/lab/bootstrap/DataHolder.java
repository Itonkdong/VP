package mk.ukim.finki.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.mk.lab.model.Event;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder
{
    public static List<Event> events;

    @PostConstruct
    public void init()
    {
        events = new ArrayList<>();
        events.add(new Event("FEIT Party", "Freshman party", 35.2));
        events.add(new Event("Tech Talk", "Seminar on emerging technologies", 42.7));
        events.add(new Event("Hackathon", "24-hour coding competition", 50.3));
        events.add(new Event("Robotics Workshop", "Introduction to robotics and automation", 30.6));
        events.add(new Event("AI Symposium", "Conference on AI and machine learning", 47.9));
        events.add(new Event("Coding Bootcamp", "Intensive coding training", 39.4));
        events.add(new Event("Startup Pitch Night", "Event for startup pitches", 45.2));
        events.add(new Event("Game Jam", "48-hour game development event", 33.8));
        events.add(new Event("Science Fair", "Annual fair showcasing student projects", 41.5));
        events.add(new Event("Art Exhibition", "Display of student artworks", 28.9));
    }
}
