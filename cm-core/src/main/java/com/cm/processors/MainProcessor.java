package com.cm.processors;

import com.cm.domain.model.Coin;
import com.cm.service.CoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainProcessor.class);

    public static void main(String[] args) {
        System.out.println("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        CoinService coinService = (CoinService) context.getBean("com.cm.service.coinService");

        Coin coin = new Coin();
        coin.setCirculation(2000L);
        coin.setComposition(Coin.CompositionType.COPPER);
        coin.setCountry("UA");
        coin.setDescription("Test 001");
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setYear("1238");

        coinService.saveCoin(coin);

        System.out.println("************** ENDING PROGRAM *****************");
    }
}
