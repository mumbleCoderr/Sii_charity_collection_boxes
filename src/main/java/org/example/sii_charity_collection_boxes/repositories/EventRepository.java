package org.example.sii_charity_collection_boxes.repositories;

import org.example.sii_charity_collection_boxes.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
