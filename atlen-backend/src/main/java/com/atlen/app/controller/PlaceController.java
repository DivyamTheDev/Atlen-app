package com.atlen.app.controller;

import com.atlen.app.entity.Place;
import com.atlen.app.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    public ResponseEntity<List<Place>> getPlaces(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String type) {
        
        if (location != null && type != null) {
            return ResponseEntity.ok(placeService.getPlacesByLocationAndType(location, type));
        } else if (type != null) {
            return ResponseEntity.ok(placeService.getPlacesByType(type));
        }
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        Optional<Place> place = placeService.getPlaceById(id);
        return place.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
