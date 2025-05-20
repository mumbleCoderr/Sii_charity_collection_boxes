package org.example.sii_charity_collection_boxes.repositories;

import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.example.sii_charity_collection_boxes.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(
            """
                SELECT e
                FROM Event e
                WHERE :collectionBox MEMBER OF e.collectionBoxes
            """
    )
    Optional<Event> findByCollectionBox(CollectionBox collectionBox);
}
