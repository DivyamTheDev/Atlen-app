package com.atlen.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itinerary_items")
@Data
@NoArgsConstructor
public class ItineraryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    private Integer dayNumber;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;

    private String notes;
}
