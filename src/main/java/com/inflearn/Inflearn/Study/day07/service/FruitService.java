package com.inflearn.Inflearn.Study.day07.service;

import com.inflearn.Inflearn.Study.day07.dto.*;
import com.inflearn.Inflearn.Study.day07.entity.Fruit;
import com.inflearn.Inflearn.Study.day07.repository.FruitRepository;
import com.inflearn.Inflearn.Study.day07.repository.FruitRepositoryUsingQuerydsl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public FruitSoldResponse getFruitIsSoldOrNot(String name) {
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
}
