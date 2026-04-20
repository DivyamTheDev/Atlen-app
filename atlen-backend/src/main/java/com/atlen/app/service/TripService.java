package com.atlen.app.service;

import com.atlen.app.dto.TripDto;
import com.atlen.app.entity.*;
import com.atlen.app.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired private TripRepository tripRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private ItineraryRepository itineraryRepository;
    @Autowired private BudgetRepository budgetRepository;
    @Autowired private PackingRepository packingRepository;

    public Trip createTrip(String userEmail, TripDto tripDto) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Trip trip = new Trip();
        trip.setUser(user);
        trip.setName(tripDto.getName());
        trip.setDestination(tripDto.getDestination());
        trip.setStartDate(tripDto.getStartDate());
        trip.setEndDate(tripDto.getEndDate());
        trip.setStatus("UPCOMING");

        return tripRepository.save(trip);
    }

    public List<Trip> getUserTrips(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return tripRepository.findByUserId(user.getId());
    }

    public Optional<Trip> getTripById(Long tripId) {
        return tripRepository.findById(tripId);
    }

    // Trip Details Methods
    public ItineraryItem addItineraryItem(Long tripId, ItineraryItem item) {
        Trip trip = getTripById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        item.setTrip(trip);
        return itineraryRepository.save(item);
    }

    public List<ItineraryItem> getItinerary(Long tripId) {
        return itineraryRepository.findByTripIdOrderByDayNumberAsc(tripId);
    }

    public BudgetItem addBudgetItem(Long tripId, BudgetItem item) {
        Trip trip = getTripById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        item.setTrip(trip);
        return budgetRepository.save(item);
    }

    public List<BudgetItem> getBudget(Long tripId) {
        return budgetRepository.findByTripId(tripId);
    }

    public PackingItem addPackingItem(Long tripId, PackingItem item) {
        Trip trip = getTripById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        item.setTrip(trip);
        return packingRepository.save(item);
    }

    public List<PackingItem> getPackingList(Long tripId) {
        return packingRepository.findByTripId(tripId);
    }
}
