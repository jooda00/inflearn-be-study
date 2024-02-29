package com.inflearn.Inflearn.Study.day07.entity;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Getter;

@Getter
public enum FruitPriceOption {
    GTE("GTE") {
      public BooleanExpression getPredicate(QFruit qFruit, Long price) {
          return qFruit.price.goe(price);
      }
    },
    LTE("LTE") {
        public BooleanExpression getPredicate(QFruit qFruit, Long price) {
            return qFruit.price.loe(price);
        }
    };

    private String param;

    FruitPriceOption(String param) {
        this.param = param;
    }

    public abstract BooleanExpression getPredicate(QFruit qFruit, Long price);
}
