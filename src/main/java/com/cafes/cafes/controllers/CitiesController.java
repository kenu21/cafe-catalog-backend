package com.cafes.cafes.controllers;

import com.cafes.cafes.dto.CityCafeCountDtoResponse;
import com.cafes.cafes.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CitiesController {
    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities/cafes-count")
    public List<CityCafeCountDtoResponse> getCafesCountByCity() {
        return cityService.getCafesCountByCity();
    }
}
