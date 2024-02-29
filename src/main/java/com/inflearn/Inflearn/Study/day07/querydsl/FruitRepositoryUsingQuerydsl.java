package com.inflearn.Inflearn.Study.day07.querydsl;

import com.inflearn.Inflearn.Study.day07.dto.FruitResponse;
import com.inflearn.Inflearn.Study.day07.dto.FruitSoldResponse;
import com.inflearn.Inflearn.Study.day07.entity.FruitPriceOption;
import com.inflearn.Inflearn.Study.day07.entity.QFruit;
import com.querydsl.core.Tuple;
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

    public FruitSoldResponse findIsSoldAndPriceByNameUsingQueryDSL(String name) {
        QFruit fruit = QFruit.fruit;
        List<Tuple> results = queryFactory
                .select(fruit.isSold, fruit.price.sum())
                .from(fruit)
                .where(fruit.name.eq(name))
                .groupBy(fruit.isSold)
                .fetch();

        Long salesAmount = 0L;
        Long noSalesAMount = 0L;

        for(Tuple res : results) {
            if(res.get(fruit.isSold)) {
                salesAmount = res.get(fruit.price.sum()).longValue();
            }
            else {
                noSalesAMount  = res.get(fruit.price.sum()).longValue();
            }
        }
        return new FruitSoldResponse(salesAmount, noSalesAMount);
    }

    public List<Tuple> findIsSoldAndPriceByNameUsingQueryDSLV2(String name) {
        QFruit fruit = QFruit.fruit;
        List<Tuple> results = queryFactory
                .select(fruit.isSold, fruit.price.sum())
                .from(fruit)
                .where(fruit.name.eq(name))
                .groupBy(fruit.isSold)
                .fetch();
        return results;
    }
}
