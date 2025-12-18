package com.cafes.cafes.specifications;

import com.cafes.cafes.entities.CafeEntity;
import com.cafes.cafes.entities.TagEntity;
import com.cafes.cafes.services.CafeService;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

public class CafeSpecifications {
    private final CafeService cafeService;

    @Autowired
    public CafeSpecifications(CafeService cafeService) {
        this.cafeService = cafeService;
    }

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
            List<Short> priceRating,
            List<BigDecimal> rating,
            List<String> tags
    ) {
        return (Root<CafeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            Predicate predicate = cb.conjunction();

            if (priceRating != null && !priceRating.isEmpty()) {
                predicate = cb.and(predicate, root.get("priceRating").in(priceRating));
            }
            if (rating != null && !rating.isEmpty()) {
                predicate = cb.and(predicate, root.get("rating").in(rating));
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
