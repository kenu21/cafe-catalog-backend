package com.cafes.cafes.controllers;

import com.cafes.cafes.CafeDtoResponse;
import com.cafes.cafes.services.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CafeController {
    private final CafeService cafeService;

    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/cafes")
    public Page<CafeDtoResponse> getCafes(Pageable pageable) {
        return cafeService.getCafes(pageable);
    }
}
