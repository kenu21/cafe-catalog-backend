package com.cafes.cafes.services;

import ch.poole.openinghoursparser.OpeningHoursParseException;
import ch.poole.openinghoursparser.OpeningHoursParser;
import ch.poole.openinghoursparser.Rule;
import com.cafes.cafes.dto.CafeDtoResponse;
import com.cafes.cafes.dto.CafeWithTagsPhotosResponseDto;
import com.cafes.cafes.entities.CafeEntity;
import com.cafes.cafes.mappers.CafeMapper;
import com.cafes.cafes.repositories.CafeRepository;
import com.cafes.cafes.specifications.CafeSpecifications;
import io.leonard.OpeningHoursEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    public CafeWithTagsPhotosResponseDto getCafeById(Long id) {
        return cafeMapper.toWithTagsPhotoResponseDto(cafeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cafe with id " + id + " not found")
                ));
    }

    public List<CafeDtoResponse> searchCafes(String query) {
        return cafeRepository
                .findAll(CafeSpecifications.search(query))
                .stream()
                .map(cafeMapper::toDto)
                .toList();
    }

    public List<CafeDtoResponse> filterCafes(
            List<Short> priceRating,
            String openingHours,
            List<BigDecimal> rating,
            List<String> tags
    ) {
        List<CafeEntity> cafes = cafeRepository
                .findAll(CafeSpecifications.filter(priceRating, rating, tags))
                .stream()
                .toList();

        if (openingHours == null || openingHours.isEmpty()) {
            return cafes.stream()
                    .map(cafeMapper::toDto)
                    .toList();
        }

        String[] parts = openingHours.split("-");
        LocalTime reqFrom = LocalTime.parse(parts[0]);
        LocalTime reqTo = LocalTime.parse(parts[1]);

        List<CafeDtoResponse> result = new ArrayList<>();

        for (CafeEntity cafeEntity : cafes) {
            String openingHoursDb = cafeEntity.getOpeningHours();
            OpeningHoursParser parser = new OpeningHoursParser(new StringReader(openingHoursDb));
            try {
                List<Rule> rules = parser.rules(false, false);

                LocalDateTime checkFrom = LocalDateTime.of(LocalDate.now(), reqFrom);
                LocalDateTime checkTo = LocalDateTime.of(LocalDate.now(), reqTo);

                boolean isOpenFrom = OpeningHoursEvaluator.isOpenAt(checkFrom, rules);
                boolean isOpenTo = OpeningHoursEvaluator.isOpenAt(checkTo.minusSeconds(1), rules);

                if (isOpenFrom && isOpenTo) {
                    result.add(cafeMapper.toDto(cafeEntity));
                }
            } catch (OpeningHoursParseException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
}
