package com.inflearn.Inflearn.Study.day07.repository;

import com.inflearn.Inflearn.Study.day07.dto.FruitResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.inflearn.Inflearn.Study.day07.entity.QFruit.fruit;

@Repository
public class FruitRepositoryUsingQuerydsl {

    private final JPAQueryFactory queryFactory;

    public FruitRepositoryUsingQuerydsl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<FruitResponse> findAllByPriceIsNotSold(String opt, Long price) {
        // 동적 쿼리
        BooleanBuilder builder = new BooleanBuilder();
        if("GTE".equals(opt)) {
            builder.and(fruit.price.goe(price));
        }
        else {
            builder.and(fruit.price.loe(price));
        }
        return queryFactory
                .selectFrom(fruit)
                .where(builder, fruit.isSold.eq(false))
                .fetch()
                .stream().map(FruitResponse::new).collect(Collectors.toList());
    }
}
