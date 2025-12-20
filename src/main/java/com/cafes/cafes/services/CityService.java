package com.cafes.cafes.services;

import com.cafes.cafes.dto.CityCafeCountDtoResponse;
import com.cafes.cafes.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public List<CityCafeCountDtoResponse> getCafesCountByCity() {
        return cityRepository.findCitiesWithCafesCount();
    }
}
