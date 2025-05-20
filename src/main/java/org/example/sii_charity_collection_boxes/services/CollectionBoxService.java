package org.example.sii_charity_collection_boxes.services;

import org.example.sii_charity_collection_boxes.dto.RegisterCollectionBoxDto;
import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.example.sii_charity_collection_boxes.repositories.CollectionBoxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CollectionBoxService {

    private final CollectionBoxRepository collectionBoxRepository;
    private final BoxMoneyService boxMoneyService;

    public CollectionBoxService(CollectionBoxRepository collectionBoxRepository, BoxMoneyService boxMoneyService) {
        this.collectionBoxRepository = collectionBoxRepository;
        this.boxMoneyService = boxMoneyService;
    }

    @Transactional
    public ResponseEntity<CollectionBox> registerCollectionBox(RegisterCollectionBoxDto collectionBoxDto){
        String uniqueIdentifier = collectionBoxDto.getIdentifier();
        Optional<CollectionBox> existingBox = collectionBoxRepository.findByIdentifier(uniqueIdentifier);
        if (existingBox.isPresent()) throw new ResponseStatusException(HttpStatus.CONFLICT, "Collection box with this identifier already exists.");

        CollectionBox collectionBox = new CollectionBox();
        collectionBox.setIdentifier(collectionBoxDto.getIdentifier());
        collectionBoxRepository.save(collectionBox);

        boxMoneyService.registerBoxMoney(collectionBoxDto.getCurrencies());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(collectionBox);
    }
}
