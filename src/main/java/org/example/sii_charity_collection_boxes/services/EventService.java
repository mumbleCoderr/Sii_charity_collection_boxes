package org.example.sii_charity_collection_boxes.services;

import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.repositories.EventRepository;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event){
        return eventRepository.save(event);
    }


}
