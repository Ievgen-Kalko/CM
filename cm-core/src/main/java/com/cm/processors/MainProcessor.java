package com.cm.processors;

import com.cm.domain.model.Coin;
import com.cm.processors.rule.CoinPriceRuleRunner;
import com.cm.service.CoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("mainProcessor")
public class MainProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainProcessor.class);

    @Autowired
    private CoinPriceRuleRunner ruleRunner;

    public static void main(String[] args) {
        LOGGER.info("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        CoinService coinService = (CoinService) context.getBean("com.cm.service.coinService");

        MainProcessor mainProcessor = (MainProcessor) context.getBean("mainProcessor");

        Coin coin = new Coin();
        coin.setCirculation(2000L);
        coin.setComposition(Coin.CompositionType.COPPER);
        coin.setCountry("UA");
        coin.setDescription("Test 001");
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setYear("1238");

        mainProcessor.processCoin(coin);

        coinService.saveCoin(coin);

        LOGGER.info("Coin persisted sucesfuly");

        LOGGER.info("************** ENDING PROGRAM *****************");
    }

    public Coin processCoin(Coin coin) {
        return ruleRunner.execute(coin);
    }
}
