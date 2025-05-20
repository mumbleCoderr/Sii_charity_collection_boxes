package org.example.sii_charity_collection_boxes.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CollectionBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String identifier;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "collectionBox", cascade = CascadeType.ALL)
    private List<BoxMoney> boxMonies;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "CollectionBox{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", event=" + event.getName() +
                ", boxMonies=" + boxMonies.stream().map(b -> b.getCurrency() + ": " + b.getAmount()).toList() +
                '}';
    }
}
