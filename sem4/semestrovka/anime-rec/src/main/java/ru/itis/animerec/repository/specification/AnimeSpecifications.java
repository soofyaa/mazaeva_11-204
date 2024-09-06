package ru.itis.animerec.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ru.itis.animerec.entity.AnimeEntity;

public class AnimeSpecifications {
//    public static Specification<AnimeEntity> topNewAnime() {
//        return new Specification<AnimeEntity>() {
//            @Override
//            public Predicate toPredicate(Root<AnimeEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                // Создаем предикат для условия, что рейтинг больше 0
//                Predicate ratingGreaterThanZero = criteriaBuilder.gt(root.get("rating"), 0);
//
//                // Добавляем условие, чтобы рейтинг был высоким (ваш определенный критерий для "высокого" рейтинга)
//                Predicate highRating = criteriaBuilder.greaterThan(root.get("rating"), 8); // Например, рейтинг выше 8
//
//                // Объединяем предикаты вместе
//                Predicate finalPredicate = criteriaBuilder.and(ratingGreaterThanZero, highRating);
//
//                // Добавляем сортировку по дате выхода (предполагая, что есть поле "releaseDate" типа Date)
//                query.orderBy(criteriaBuilder.desc(root.get("releaseDate")));
//
//                return finalPredicate;
//            }
//        };
//    }
}