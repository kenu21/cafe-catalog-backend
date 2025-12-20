package com.cafes.cafes.repositories;

import com.cafes.cafes.dto.CityCafeCountDtoResponse;
import com.cafes.cafes.entities.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {

    @Query(value = """
                SELECT c.name AS cityName, COUNT(cf.id) AS cafesCount
                FROM cities c
                LEFT JOIN streets s ON s.city_id = c.id
                LEFT JOIN addresses a ON a.street_id = s.id
                LEFT JOIN cafes cf ON cf.address_id = a.id
                GROUP BY c.id, c.name
            """, nativeQuery = true)
    List<CityCafeCountDtoResponse> findCitiesWithCafesCount();
}
