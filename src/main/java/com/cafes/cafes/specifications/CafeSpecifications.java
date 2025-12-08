package com.cafes.cafes.specifications;

import com.cafes.cafes.entities.CafeEntity;
import org.springframework.data.jpa.domain.Specification;

public class CafeSpecifications {

    public static Specification<CafeEntity> nameContains(String text) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + text.toLowerCase() + "%");
    }

    public static Specification<CafeEntity> streetContains(String text) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("addressEntity").join("streetEntity").get("name")),
                        "%" + text.toLowerCase() + "%"
                );
    }

    public static Specification<CafeEntity> cityContains(String text) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(
                                root.join("addressEntity")
                                        .join("streetEntity")
                                        .join("cityEntity")
                                        .get("name")),
                        "%" + text.toLowerCase() + "%"
                );
    }

    public static Specification<CafeEntity> buildingNumberContains(String text) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.join("addressEntity").get("buildingNumber")),
                        "%" + text.toLowerCase() + "%"
                );
    }

    public static Specification<CafeEntity> search(String text) {
        return Specification.where(nameContains(text))
                .or(streetContains(text))
                .or(cityContains(text))
                .or(buildingNumberContains(text));
    }
}
