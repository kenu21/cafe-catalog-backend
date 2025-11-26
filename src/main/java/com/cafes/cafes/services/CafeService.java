package com.cafes.cafes.services;

import com.cafes.cafes.CafeDtoResponse;
import com.cafes.cafes.entities.CafeEntity;
import com.cafes.cafes.mappers.CafeMapper;
import com.cafes.cafes.repositories.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CafeService {
    private final CafeRepository cafeRepository;
    private final CafeMapper cafeMapper;

    @Autowired
    public CafeService(CafeRepository cafeRepository, CafeMapper cafeMapper) {
        this.cafeRepository = cafeRepository;
        this.cafeMapper = cafeMapper;
    }

    public Page<CafeDtoResponse> getCafes(Pageable pageable) {
        return cafeRepository.findAll(pageable).map(cafeMapper::toDto);
    }
}
