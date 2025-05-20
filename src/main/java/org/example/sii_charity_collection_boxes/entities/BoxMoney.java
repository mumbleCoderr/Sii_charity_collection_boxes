package org.example.sii_charity_collection_boxes.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class BoxMoney {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "collection_box_id")
    private CollectionBox collectionBox;

    private String currency;
    private BigDecimal amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CollectionBox getCollectionBox() {
        return collectionBox;
    }

    public void setCollectionBox(CollectionBox collectionBox) {
        this.collectionBox = collectionBox;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BoxMoney{" +
                "id=" + id +
                ", collectionBox=" + collectionBox.getIdentifier() +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
