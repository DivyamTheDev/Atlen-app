package com.atlen.app.service;

import com.atlen.app.entity.Place;
import com.atlen.app.repository.PlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository placeRepository;

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public List<Place> getPlacesByType(String type) {
        return placeRepository.findByType(type);
    }

    public List<Place> getPlacesByLocationAndType(String location, String type) {
        return placeRepository.findByLocationAndType(location, type);
    }

    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }
}
