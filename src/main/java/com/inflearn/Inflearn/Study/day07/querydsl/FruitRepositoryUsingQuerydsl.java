package com.inflearn.Inflearn.Study.day07.querydsl;

import com.inflearn.Inflearn.Study.day07.dto.FruitResponse;
import com.inflearn.Inflearn.Study.day07.entity.FruitPriceOption;
import com.inflearn.Inflearn.Study.day07.entity.QFruit;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FruitRepositoryUsingQuerydsl {

    private final JPAQueryFactory queryFactory;

    public FruitRepositoryUsingQuerydsl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

/*    public List<FruitResponse> findAllByPriceIsNotSold(String opt, Long price) {
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
    }*/

    public List<FruitResponse> findAllByPriceIsNotSold(FruitPriceOption opt, Long price) {
        QFruit fruit = QFruit.fruit;
        return queryFactory
                .selectFrom(fruit)
                .where(opt.getPredicate(fruit, price), fruit.isSold.eq(false))
                .fetch()
                .stream().map(FruitResponse::new).collect(Collectors.toList());
    }

}
