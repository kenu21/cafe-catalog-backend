package com.cafes.cafes.controllers;

import com.cafes.cafes.dto.CafeDtoResponse;
import com.cafes.cafes.dto.CafeWithTagsResponseDto;
import com.cafes.cafes.services.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CafeController {
    private final CafeService cafeService;

    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/cafes")
    public Page<CafeDtoResponse> getCafes(
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return cafeService.getCafes(sort, page, size);
    }

    @GetMapping("/cafes/{id}")
    public CafeWithTagsResponseDto getCafeById(@PathVariable Long id) {
        return cafeService.getCafeById(id);
    }

    @GetMapping("/search")
    public List<CafeDtoResponse> search(@RequestParam String query) {
        return cafeService.searchCafes(query);
    }

    @GetMapping("/filter")
    public List<CafeDtoResponse> filterCafes(
            @RequestParam(required = false) Short priceRating,
            @RequestParam(required = false) String openingHours,
            @RequestParam(required = false) BigDecimal rating,
            @RequestParam(required = false) List<String> tags
    ) {
        return cafeService.filterCafes(priceRating, openingHours, rating, tags);
    }
}
