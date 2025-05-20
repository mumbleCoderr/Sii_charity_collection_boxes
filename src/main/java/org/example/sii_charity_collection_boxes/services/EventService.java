package org.example.sii_charity_collection_boxes.services;

import org.example.sii_charity_collection_boxes.dto.FinancialReportDto;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event addEvent(Event event){
        return eventRepository.save(event);
    }

    public List<FinancialReportDto> getFinancialReport(){
        List<Event> events = eventRepository.findAll();
        events.forEach(System.out::println);
        List<FinancialReportDto> financialReport = new ArrayList<>();
        events.forEach(e -> financialReport.add(new FinancialReportDto(e.getName(), e.getBalance(), e.getCurrency())));

        return financialReport;
    }
}
