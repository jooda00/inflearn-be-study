package com.inflearn.Inflearn.Study.day07.service;

import com.inflearn.Inflearn.Study.day07.dto.*;
import com.inflearn.Inflearn.Study.day07.entity.Fruit;
import com.inflearn.Inflearn.Study.day07.entity.FruitPriceOption;
import com.inflearn.Inflearn.Study.day07.querydsl.FruitRepositoryUsingQuerydsl;
import com.inflearn.Inflearn.Study.day07.repository.FruitRepository;
import com.querydsl.core.Tuple;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.inflearn.Inflearn.Study.day07.entity.QFruit.fruit;

@Service // 스프링 빈으로 만들어 줌
@Transactional(readOnly = true) // default값이 false
public class FruitService {

    private final FruitRepository fruitRepository;
    private final FruitRepositoryUsingQuerydsl fruitRepositoryUsingQuerydsl;

    public FruitService(FruitRepository fruitRepository, FruitRepositoryUsingQuerydsl fruitRepositoryUsingQuerydsl) {
        this.fruitRepository = fruitRepository;
        this.fruitRepositoryUsingQuerydsl = fruitRepositoryUsingQuerydsl;
    }

    @Transactional
    public void saveFruit(FruitRequest request) {
        Fruit fruit = new Fruit(request.getName(), request.getWarehousingDate(), request.getPrice());
        fruitRepository.save(fruit);
    }

    @Transactional
    public void sellFruit(FruitUpdateRequest updateRequest) {
        Optional<Fruit> fruit = Optional.ofNullable(fruitRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new IllegalArgumentException("데이터베이스에 과일이 없습니다.")));
        fruit.get().soldFruit();
    }

/*    public FruitSoldResponse getFruitIsSoldOrNot(String name) {
        List<Fruit> fruit = fruitRepository.findByName(name);
        if(fruit.size() == 0) {
            throw new IllegalArgumentException("이름과 일치하는 과일이 없습니다.");
        }
        long startTime = System.currentTimeMillis();
        List<Object[]> fruits = fruitRepository.findIsSoldAndPriceByName(name);
        Long isSold = 0L;
        Long isNotSold = 0L;
        for(Object[] res : fruits) {
            if((Boolean) res[0]) {
                isSold = ((BigDecimal) res[1]).longValue();
            }
            else {
                isNotSold = ((BigDecimal) res[1]).longValue();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("걸린 시간 : " + (endTime - startTime));
        return new FruitSoldResponse(isSold, isNotSold);
    }*/

    public FruitSoldResponse getFruitIsSoldOrNotV1(String name) {
        long startTime = System.currentTimeMillis();
        if(fruitRepository.findByName(name).size() == 0) {
            throw new IllegalArgumentException("이름과 일치하는 과일이 없습니다.");
        }
        FruitSoldResponse fruitSoldResponse = fruitRepositoryUsingQuerydsl.findIsSoldAndPriceByNameUsingQueryDSL(name);
        long endTime = System.currentTimeMillis();
        System.out.println("실행 시간 v1 : " + (endTime - startTime));
        return fruitSoldResponse;
    }

    public FruitSoldResponse getFruitIsSoldOrNotV2(String name) {
        long startTime = System.currentTimeMillis();
        List<Tuple> result = fruitRepositoryUsingQuerydsl.findIsSoldAndPriceByNameUsingQueryDSLV2(name);
        if(result.size() == 0) {
            throw new IllegalArgumentException("이름과 일치하는 과일이 없습니다.");
        }
        Long salesAmount = 0L;
        Long noSalesAMount = 0L;

        for(Tuple res : result) {
            if(res.get(fruit.isSold)) {
                salesAmount = res.get(fruit.price.sum()).longValue();
            }
            else {
                noSalesAMount  = res.get(fruit.price.sum()).longValue();
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("실행 시간 v2 : " + (endTime - startTime));
        return new FruitSoldResponse(salesAmount, noSalesAMount);
    }

    public FruitCountResponse getFruitCountByName(String name) {
        return new FruitCountResponse(fruitRepository.countByName(name));
    }

    public List<FruitResponse> getFruitGreaterThanEqualByPrice(Long price) {
        return fruitRepository.findAllByPriceGreaterThanEqualAndIsSoldFalse(price)
                .stream()
                .map(FruitResponse::new)
                .collect(Collectors.toList());
    }

    public List<FruitResponse> getFruitLessThanEqualByPrice(Long price) {
        return fruitRepository.findAllByPriceLessThanEqualAndIsSoldFalse(price)
                .stream()
                .map(FruitResponse::new)
                .collect(Collectors.toList());
    }

    public List<FruitResponse> getFruitByPriceIsSoldFalse(FruitPriceOption opt, Long price) {
        return fruitRepositoryUsingQuerydsl.findAllByPriceIsNotSold(opt, price);
    }
}
