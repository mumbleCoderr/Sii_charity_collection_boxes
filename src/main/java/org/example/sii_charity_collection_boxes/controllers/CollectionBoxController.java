package org.example.sii_charity_collection_boxes.controllers;

import org.example.sii_charity_collection_boxes.dto.BoxMoneyAmountDto;
import org.example.sii_charity_collection_boxes.dto.CollectionBoxResponseDto;
import org.example.sii_charity_collection_boxes.dto.RegisterCollectionBoxDto;
import org.example.sii_charity_collection_boxes.entities.BoxMoney;
import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.services.CollectionBoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/collectionbox")
public class CollectionBoxController {

    private final CollectionBoxService collectionBoxService;

    public CollectionBoxController(CollectionBoxService collectionBoxService) {
        this.collectionBoxService = collectionBoxService;
    }

    @PostMapping("/register")
    public ResponseEntity<CollectionBox> registerCollectionBox(@RequestBody RegisterCollectionBoxDto collectionBoxDto){
        return collectionBoxService.registerCollectionBox(collectionBoxDto);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<CollectionBoxResponseDto>> getAllCollectionBoxes(){
        return collectionBoxService.getAllCollectionBoxes();
    }

    @DeleteMapping("/unregister/{id}")
    public ResponseEntity<Void> unregisterCollectionBox(@PathVariable("id") long id){
        return collectionBoxService.unregisterCollectionBox(id);
    }

    @PatchMapping("/assign/{boxId}")
    public ResponseEntity<CollectionBox> assignCollectionBoxToEvent(@PathVariable long boxId, @RequestParam long eventId){
        return collectionBoxService.assignCollectionBoxToEvent(boxId, eventId);
    }

    @PatchMapping("/putmoney/{boxId}")
    public ResponseEntity<BoxMoneyAmountDto> putMoney(@PathVariable long boxId, @RequestParam String currency, @RequestParam BigDecimal amount){
        return collectionBoxService.putMoney(boxId, currency, amount);
    }

    @PatchMapping("/transfermoney/{boxId}")
    public ResponseEntity<Event> transferMoney(@PathVariable long boxId){
        return collectionBoxService.transferMoney(boxId);
    }
}
