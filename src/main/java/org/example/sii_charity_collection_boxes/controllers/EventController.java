package org.example.sii_charity_collection_boxes.controllers;

import org.example.sii_charity_collection_boxes.dto.FinancialReportDto;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getfinancialreport")
    public List<FinancialReportDto> getFinancialReport(){
        return eventService.getFinancialReport();
    }
}
