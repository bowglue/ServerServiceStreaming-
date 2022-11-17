package com.serverapp.trailerservice.Repositories;

import com.serverapp.trailerservice.models.TrailerManifest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TrailerManifestRepository extends CrudRepository<TrailerManifest, Long> {
    @Query(value = "SELECT m FROM trailer_manifest m where m.video_name = ?1 ")
    TrailerManifest findManifest(String name);
}
