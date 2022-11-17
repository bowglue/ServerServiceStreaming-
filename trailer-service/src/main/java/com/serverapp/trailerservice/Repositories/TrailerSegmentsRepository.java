package com.serverapp.trailerservice.Repositories;

import com.serverapp.trailerservice.models.TrailerSegment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TrailerSegmentsRepository extends CrudRepository<TrailerSegment, Long> {
    @Query(value = "SELECT m FROM trailer_segment m where m.segment_name = ?1")
    TrailerSegment findSegment(String name);
}
