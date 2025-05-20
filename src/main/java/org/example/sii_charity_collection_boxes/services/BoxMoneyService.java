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
import java.util.Set;

@Service
public class BoxMoneyService {
    private final BoxMoneyRepository boxMoneyRepository;

    public BoxMoneyService(BoxMoneyRepository boxMoneyRepository) {
        this.boxMoneyRepository = boxMoneyRepository;
    }

    public List<BoxMoney> registerBoxMoney(Set<String> currencies, CollectionBox collectionBox){
        List<BoxMoney> boxMonies = new ArrayList<>();

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
