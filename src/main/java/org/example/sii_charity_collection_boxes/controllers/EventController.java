package org.example.sii_charity_collection_boxes.controllers;

import org.example.sii_charity_collection_boxes.dto.FinancialReportResponseDto;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.services.EventService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Event> addEvent(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @GetMapping("/getfinancialreport")
    public ResponseEntity<List<FinancialReportResponseDto>> getFinancialReport(){
        return eventService.getFinancialReport();
    }
}
