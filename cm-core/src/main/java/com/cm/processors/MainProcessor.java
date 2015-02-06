package com.cm.processors;

import com.cm.domain.model.Coin;
import com.cm.helpers.CoinPriceRuleHelper;
import com.cm.service.CoinService;
import com.cm.util.CmGenericException;
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
    private CoinPriceRuleHelper ruleRunner;

    public static void main(String[] args) throws CmGenericException {
        LOGGER.info("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        CoinService coinService = (CoinService) context.getBean("com.cm.service.coinService");

        MainProcessor mainProcessor = (MainProcessor) context.getBean("mainProcessor");

        Coin coin = new Coin();
        coin.setCirculation(2000L);
        coin.setComposition(Coin.CompositionType.SILVER);
        coin.setCountry("US");
        coin.setDescription("Test 001");
        coin.setGrade(Coin.GradeType.VERY_FINE);
        coin.setYear(1935);

        try {
            coinService.processNewCoin(coin);
        } catch (CmGenericException e) {
            //TODO - Write coin's parameters
            LOGGER.error("Error occurred during processing new coin");
            throw e;
        }

        LOGGER.info("************** ENDING PROGRAM *****************");
    }
}
