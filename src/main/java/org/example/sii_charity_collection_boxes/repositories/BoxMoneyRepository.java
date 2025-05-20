package org.example.sii_charity_collection_boxes.repositories;

import org.example.sii_charity_collection_boxes.entities.BoxMoney;
import org.example.sii_charity_collection_boxes.entities.CollectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoxMoneyRepository extends JpaRepository<BoxMoney, Long> {

    @Query(
            """
                SELECT b
                FROM BoxMoney b
                WHERE b.collectionBox = :collectionBox
            """
    )
    Optional<List<BoxMoney>> findByCollectionBox(CollectionBox collectionBox);

    @Query(
            """
                SELECT b 
                FROM BoxMoney b
                WHERE b.collectionBox = :collectionBox
                AND b.currency = :currency
            """
    )
    Optional<BoxMoney> findByCollectionBoxWithCurrency(CollectionBox collectionBox, String currency);
}
