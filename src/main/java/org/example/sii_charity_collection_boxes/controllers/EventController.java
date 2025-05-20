package org.example.sii_charity_collection_boxes.controllers;

import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.services.EventService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/add")
    public Event addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }
}
