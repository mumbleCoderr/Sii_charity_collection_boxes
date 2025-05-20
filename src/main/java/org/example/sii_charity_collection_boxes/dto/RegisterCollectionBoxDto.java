package org.example.sii_charity_collection_boxes.dto;

import java.util.Set;

public class RegisterCollectionBoxDto {
    private String identifier;
    private Set<String> currencies;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Set<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Set<String> currencies) {
        this.currencies = currencies;
    }
}
