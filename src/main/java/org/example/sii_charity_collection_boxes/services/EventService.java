package org.example.sii_charity_collection_boxes.services;

import org.example.sii_charity_collection_boxes.dto.FinancialReportResponseDto;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.repositories.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public ResponseEntity<Event> addEvent(Event event){
        eventRepository.save(event);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(event);
    }

    public ResponseEntity<List<FinancialReportResponseDto>> getFinancialReport(){
        List<Event> events = eventRepository.findAll();
        List<FinancialReportResponseDto> financialReport = new ArrayList<>();
        events.forEach(e -> {
            FinancialReportResponseDto financialReportResponseDto = new FinancialReportResponseDto();
            financialReportResponseDto.setEventName(e.getName());
            financialReportResponseDto.setBalance(e.getBalance());
            financialReportResponseDto.setCurrency(e.getCurrency());
            financialReport.add(financialReportResponseDto);
        });

        return ResponseEntity.ok(financialReport);
    }
}
