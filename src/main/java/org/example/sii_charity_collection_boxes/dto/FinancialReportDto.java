package org.example.sii_charity_collection_boxes.dto;

import java.math.BigDecimal;

public class FinancialReportDto {
    private String eventName;
    private BigDecimal balance;
    private String currency;

    public FinancialReportDto(String eventName, BigDecimal balance, String currency) {
        this.eventName = eventName;
        this.balance = balance;
        this.currency = currency;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
