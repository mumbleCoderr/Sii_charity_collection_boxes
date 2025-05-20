package org.example.sii_charity_collection_boxes.services;

import org.example.sii_charity_collection_boxes.dto.BoxMoneyAmountDto;
import org.example.sii_charity_collection_boxes.dto.CollectionBoxResponseDto;
import org.example.sii_charity_collection_boxes.dto.RegisterCollectionBoxDto;
import org.example.sii_charity_collection_boxes.entities.BoxMoney;
import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.repositories.CollectionBoxRepository;
import org.example.sii_charity_collection_boxes.repositories.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CollectionBoxService {

    private final CollectionBoxRepository collectionBoxRepository;
    private final EventRepository eventRepository;
    private final BoxMoneyService boxMoneyService;

    public CollectionBoxService(CollectionBoxRepository collectionBoxRepository,
                                EventRepository eventRepository, BoxMoneyService boxMoneyService) {
        this.collectionBoxRepository = collectionBoxRepository;
        this.eventRepository = eventRepository;
        this.boxMoneyService = boxMoneyService;
    }

    @Transactional
    public ResponseEntity<CollectionBox> registerCollectionBox(RegisterCollectionBoxDto collectionBoxDto){
        if (collectionBoxDto.getCurrencies().size() > 3)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Collection box can hold up to 3 different currencies.");
        String uniqueIdentifier = collectionBoxDto.getIdentifier();
        Optional<CollectionBox> existingBox = collectionBoxRepository.findByIdentifier(uniqueIdentifier);
        if (existingBox.isPresent())
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Collection box with this identifier already exists.");

        CollectionBox collectionBox = new CollectionBox();
        collectionBox.setIdentifier(collectionBoxDto.getIdentifier());
        collectionBoxRepository.save(collectionBox);

        boxMoneyService.registerBoxMoney(collectionBoxDto.getCurrencies(), collectionBox);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(collectionBox);
    }

    public ResponseEntity<List<CollectionBoxResponseDto>> getAllCollectionBoxes(){
        List<CollectionBoxResponseDto> collectionBoxResponseDtos = new ArrayList<>();
        List<CollectionBox> collectionBoxes = collectionBoxRepository.findAll();

        collectionBoxes.forEach(c -> {
            CollectionBoxResponseDto collectionBoxResponseDto = new CollectionBoxResponseDto();
            collectionBoxResponseDto.setIdentifier(c.getIdentifier());
            collectionBoxResponseDto.setAssigned(c.getEvent() != null);
            Map<String, BigDecimal> amounts = boxMoneyService.getBoxesMoneyAmounts(c);
            if(amounts.values().stream().allMatch(amount -> amount.compareTo(BigDecimal.ZERO) == 0))
                collectionBoxResponseDto.setEmpty(true);
            collectionBoxResponseDtos.add(collectionBoxResponseDto);
        });
        return ResponseEntity.ok(collectionBoxResponseDtos);
    }

    public ResponseEntity<Void> unregisterCollectionBox(long id){
        CollectionBox collectionBox = collectionBoxRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Collection box not found."));
        collectionBoxRepository.delete(collectionBox);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<CollectionBox> assignCollectionBoxToEvent(long boxId, long eventId){
        CollectionBox collectionBox = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new NoSuchElementException("Collection box not found."));
        Event event = eventRepository.findById(eventId)
                        .orElseThrow(() -> new NoSuchElementException("Event not found."));
        collectionBox.setEvent(event);
        collectionBoxRepository.save(collectionBox);
        return ResponseEntity.ok(collectionBox);
    }

    public ResponseEntity<BoxMoneyAmountDto> putMoney(long boxId, String currency, BigDecimal amount){
        CollectionBox collectionBox = collectionBoxRepository.findById(boxId)
                .orElseThrow(() -> new NoSuchElementException("Collection box not found."));
        BoxMoney boxMoney = boxMoneyService.putMoney(collectionBox, currency, amount);
        BoxMoneyAmountDto boxMoneyAmountDto = new BoxMoneyAmountDto();
        boxMoneyAmountDto.setCurrency(boxMoney.getCurrency());
        boxMoneyAmountDto.setAmount(boxMoney.getAmount());
        return ResponseEntity.ok(boxMoneyAmountDto);
    }
}
