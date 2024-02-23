package com.inflearn.Inflearn.Study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InflearnStudyApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(InflearnStudyApplication.class, args);
//		Week01Main week01Main = context.getBean(Week01Main.class);
//		week01Main.doSomething();
	}

}
