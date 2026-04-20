package com.atlen.app.controller;

import com.atlen.app.dto.TripDto;
import com.atlen.app.entity.BudgetItem;
import com.atlen.app.entity.ItineraryItem;
import com.atlen.app.entity.PackingItem;
import com.atlen.app.entity.Trip;
import com.atlen.app.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<Trip> createTrip(Authentication authentication, @RequestBody TripDto tripDto) {
        String email = authentication.getName();
        return ResponseEntity.ok(tripService.createTrip(email, tripDto));
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getUserTrips(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(tripService.getUserTrips(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTrip(@PathVariable Long id) {
        return tripService.getTripById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Itinerary Endpoints
    @PostMapping("/{id}/itinerary")
    public ResponseEntity<ItineraryItem> addItineraryItem(@PathVariable Long id, @RequestBody ItineraryItem item) {
        return ResponseEntity.ok(tripService.addItineraryItem(id, item));
    }

    @GetMapping("/{id}/itinerary")
    public ResponseEntity<List<ItineraryItem>> getItinerary(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getItinerary(id));
    }

    // Budget Endpoints
    @PostMapping("/{id}/budget")
    public ResponseEntity<BudgetItem> addBudgetItem(@PathVariable Long id, @RequestBody BudgetItem item) {
        return ResponseEntity.ok(tripService.addBudgetItem(id, item));
    }

    @GetMapping("/{id}/budget")
    public ResponseEntity<List<BudgetItem>> getBudget(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getBudget(id));
    }

    // Packing List Endpoints
    @PostMapping("/{id}/packing")
    public ResponseEntity<PackingItem> addPackingItem(@PathVariable Long id, @RequestBody PackingItem item) {
        return ResponseEntity.ok(tripService.addPackingItem(id, item));
    }

    @GetMapping("/{id}/packing")
    public ResponseEntity<List<PackingItem>> getPackingList(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getPackingList(id));
    }
}
