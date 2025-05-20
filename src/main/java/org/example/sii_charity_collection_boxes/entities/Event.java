package org.example.sii_charity_collection_boxes.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "event")
    private List<CollectionBox> collectionBoxes;
    private String name;
    private String currency;
    private BigDecimal balance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<CollectionBox> getCollectionBoxes() {
        return collectionBoxes;
    }

    public void setCollectionBoxes(List<CollectionBox> collectionBoxes) {
        this.collectionBoxes = collectionBoxes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", collectionBoxes=" + collectionBoxes.stream().map(b -> b.getIdentifier()).toList() +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                '}';
    }
}
