package com.atlen.app.repository;

import com.atlen.app.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByType(String type);
    List<Place> findByLocationAndType(String location, String type);
}
