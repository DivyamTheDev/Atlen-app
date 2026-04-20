package com.atlen.app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "places")
@Data
@NoArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    // HOTEL, RESTAURANT, ATTRACTION
    private String type;
    
    private String location;
    private Double rating;
    
    @Column(length = 1000)
    private String description;
    
    // Budget-Friendly, Premium, Luxury Dining, etc.
    private String priceLevel;
    
    private String imageUrl;
}
