package org.example.sii_charity_collection_boxes.dto;

public class RegisterCollectionBoxDto {
    private String identifier;
    private String[] currencies;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String[] getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String[] currencies) {
        this.currencies = currencies;
    }
}
