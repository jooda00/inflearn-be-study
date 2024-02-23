package com.inflearn.Inflearn.Study.day01;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// @interface [어노테이션 이름] --> 커스텀 어노테이션
public @interface CustomAnnotation {
}
