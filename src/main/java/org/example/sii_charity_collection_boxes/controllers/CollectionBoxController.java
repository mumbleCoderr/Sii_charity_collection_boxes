package org.example.sii_charity_collection_boxes.controllers;

import org.example.sii_charity_collection_boxes.dto.RegisterCollectionBoxDto;
import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.example.sii_charity_collection_boxes.services.CollectionBoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
