package com.cafes.cafes.specifications;

import com.cafes.cafes.entities.CafeEntity;
import com.cafes.cafes.entities.TagEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

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

    public static Specification<CafeEntity> filter(
            Short minPriceRating,
            String openingHours,
            BigDecimal minRating,
            List<String> tags
    ) {
        return (Root<CafeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (minPriceRating != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("priceRating"), minPriceRating));
            }
            if (openingHours != null && !openingHours.isEmpty()) {
                predicate = cb.and(predicate, cb.like(root.get("openingHours"), "%" + openingHours + "%"));
            }
            if (minRating != null) {
                predicate = cb.and(predicate, cb.greaterThanOrEqualTo(root.get("rating"), minRating));
            }
            if (tags != null && !tags.isEmpty()) {
                Join<CafeEntity, TagEntity> tagsJoin = root.join("tags", JoinType.LEFT);
                predicate = cb.and(predicate, tagsJoin.get("name").in(tags));
                query.distinct(true);
            }

            return predicate;
        };
    }
}
