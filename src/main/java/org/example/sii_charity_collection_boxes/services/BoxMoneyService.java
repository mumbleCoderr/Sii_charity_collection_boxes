package org.example.sii_charity_collection_boxes.services;

import org.example.sii_charity_collection_boxes.entities.BoxMoney;
import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.example.sii_charity_collection_boxes.repositories.BoxMoneyRepository;
import org.example.sii_charity_collection_boxes.repositories.CollectionBoxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BoxMoneyService {
    private final BoxMoneyRepository boxMoneyRepository;
    private final CurrencyService currencyService;

    public BoxMoneyService(BoxMoneyRepository boxMoneyRepository, CurrencyService currencyService) {
        this.boxMoneyRepository = boxMoneyRepository;
        this.currencyService = currencyService;
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

    public Map<String, BigDecimal> getBoxesMoneyAmounts(CollectionBox collectionBox){
        List<BoxMoney> boxMonies = boxMoneyRepository.findByCollectionBox(collectionBox)
                .orElseThrow(() -> new NoSuchElementException("Money boxes not found."));

        Map<String, BigDecimal> amounts = new HashMap<>();
        boxMonies.forEach(b -> amounts.put(b.getCurrency(), b.getAmount()));
        return amounts;
    }

    public BoxMoney putMoney(CollectionBox collectionBox, String currency, BigDecimal amount){
        BoxMoney boxMoney = boxMoneyRepository.findByCollectionBoxWithCurrency(collectionBox, currency)
                .orElseThrow(() -> new NoSuchElementException("Box money with this currency not found"));

        boxMoney.setAmount(boxMoney.getAmount().add(amount));
        boxMoneyRepository.save(boxMoney);
        return boxMoney;
    }

    public BigDecimal transferMoney(CollectionBox collectionBox, Event event){
        List<BoxMoney> boxMonies = boxMoneyRepository.findByCollectionBox(collectionBox)
                .orElseThrow(() -> new NoSuchElementException("Money boxes not found."));

        BigDecimal totalConvertedAmount = new BigDecimal(0);
        for (BoxMoney boxMoney : boxMonies){
            if(boxMoney.getAmount().compareTo(BigDecimal.ZERO) > 0){
                BigDecimal convertedAmount = currencyService.convertCurrency(boxMoney.getCurrency(), event.getCurrency(), boxMoney.getAmount());
                totalConvertedAmount = totalConvertedAmount.add(convertedAmount);
                boxMoney.setAmount(new BigDecimal(0));
            }
        }
        return totalConvertedAmount;
    }
}
