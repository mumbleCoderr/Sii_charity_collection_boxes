package org.example.sii_charity_collection_boxes.repositories;

import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CollectionBoxRepository extends JpaRepository<CollectionBox, Long> {

    @Query(
            """
                SELECT c 
                FROM CollectionBox c
                WHERE c.identifier = :identifier
            """
    )
    Optional<CollectionBox> findByIdentifier(String identifier);
}
