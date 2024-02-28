package com.inflearn.Inflearn.Study.day07.repository;

import com.inflearn.Inflearn.Study.day07.dto.FruitResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.inflearn.Inflearn.Study.day07.entity.QFruit.fruit;

@Repository
public class FruitRepositoryUsingQuerydsl {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public FruitRepositoryUsingQuerydsl(EntityManager em, JPAQueryFactory queryFactory) {
        this.em = em;
        this.queryFactory = queryFactory;
    }

    public List<FruitResponse> findAllByPriceIsNotSold(String opt, Long price) {
        if(opt.equals("GTE")) {
            return queryFactory
                    .selectFrom(fruit)
                    .where(fruit.price.goe(price), fruit.isSold.eq(false))
                    .fetch()
                    .stream()
                    .map(FruitResponse::new)
                    .collect(Collectors.toList());
        }
        return queryFactory
                .selectFrom(fruit)
                .where(fruit.price.loe(price), fruit.isSold.eq(false))
                .fetch()
                .stream()
                .map(FruitResponse::new)
                .collect(Collectors.toList());
    }

}
