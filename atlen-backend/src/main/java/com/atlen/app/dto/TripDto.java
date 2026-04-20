package com.atlen.app.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TripDto {
    private String name;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
}
