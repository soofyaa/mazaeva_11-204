package ru.itis.animerec.repository.specification;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import ru.itis.animerec.entity.AnimeEntity;
import ru.itis.animerec.repository.AnimeRepositoryCustom;
import java.util.List;

@Repository
public class AnimeRepositoryCustomImpl implements AnimeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AnimeEntity> findTop100ByYearAndRating() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AnimeEntity> cq = cb.createQuery(AnimeEntity.class);
        Root<AnimeEntity> root = cq.from(AnimeEntity.class);

        root.fetch("genres", JoinType.LEFT);
        root.fetch("creators", JoinType.LEFT);
        root.fetch("studios", JoinType.LEFT);

        // Создаем выражение для выбора уникальных записей по названию
        Expression<String> titleExpression = root.get("title");
        Subquery<Long> subquery = cq.subquery(Long.class);
        Root<AnimeEntity> subRoot = subquery.from(AnimeEntity.class);
        subquery.select(cb.min(subRoot.get("id"))).groupBy(subRoot.get("title"));

        Predicate ratingPredicate = cb.gt(root.get("rating"), 0);

        Order yearOrder = cb.desc(root.get("year"));
        Order ratingOrder = cb.desc(root.get("rating"));

        cq.where(cb.and(ratingPredicate, cb.in(root.get("id")).value(subquery)));

        cq.orderBy(yearOrder, ratingOrder);

        return entityManager.createQuery(cq)
                .setMaxResults(100)
                .getResultList();
    }

}
