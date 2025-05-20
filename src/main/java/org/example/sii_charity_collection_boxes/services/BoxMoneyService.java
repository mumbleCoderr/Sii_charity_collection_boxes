package org.example.sii_charity_collection_boxes.services;

import org.example.sii_charity_collection_boxes.entities.BoxMoney;
import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.example.sii_charity_collection_boxes.repositories.BoxMoneyRepository;
import org.example.sii_charity_collection_boxes.repositories.CollectionBoxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoxMoneyService {
    private final BoxMoneyRepository boxMoneyRepository;
    private final CollectionBoxRepository collectionBoxRepository;

    public BoxMoneyService(BoxMoneyRepository boxMoneyRepository, CollectionBoxRepository collectionBoxRepository) {
        this.boxMoneyRepository = boxMoneyRepository;
        this.collectionBoxRepository = collectionBoxRepository;
    }

    public List<BoxMoney> registerBoxMoney(String[] currencies, long id){
        List<BoxMoney> boxMonies = new ArrayList<>();
        CollectionBox collectionBox = collectionBoxRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Collection box not found"));
        for (String currency : currencies){
            if(currency != null){
                BoxMoney boxMoney = new BoxMoney();
                boxMoney.setCurrency(currency);
                boxMoney.setAmount(new BigDecimal(0));
                boxMoney.setCollectionBox(collectionBox);
                boxMoneyRepository.save(boxMoney);
                boxMonies.add(boxMoney);
            }
        }
        return boxMonies;
    }
}
