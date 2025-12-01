package com.cafes.cafes.services;

import com.cafes.cafes.CafeDtoResponse;
import com.cafes.cafes.mappers.CafeMapper;
import com.cafes.cafes.repositories.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<CafeDtoResponse> getCafes(String sort, int page, int size) {
        Sort sorting = switch (sort) {
            case "rating" -> Sort.by("rating").descending();
            case "votes" -> Sort.by("votesCount").descending();
            case "newest" -> Sort.by("createdAt").descending();
            default -> Sort.by("id").ascending();
        };

        Pageable pageable = PageRequest.of(page, size, sorting);
        return cafeRepository.findAll(pageable).map(cafeMapper::toDto);
    }
}
