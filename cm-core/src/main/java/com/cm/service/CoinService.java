package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.persistence.jpa.CoinRepositoryJPA;
import com.cm.helpers.MailHelper;
import com.cm.helpers.CoinPriceRuleHelper;
import com.cm.util.CmGenericException;
import com.cm.util.GradePriceFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service("com.cm.service.coinService")
@Transactional
public class CoinService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinService.class);

    @Autowired
    private CoinRepositoryJPA coinRepositoryJPA;

    @Autowired
    private CoinPriceRuleHelper priceRuleRunner;

    @Autowired
    private MailHelper mailProcessor;

    public CoinService() {
    }

    public void processNewCoin(Coin coin) throws CmGenericException {
        Assert.notNull(coin, "method was invoked with null arg");

        checkCoinParameters(coin);

        priceRuleRunner.execute(coin);

        processCoinPrice(coin);

        saveCoin(coin);

        mailProcessor.sendMail("com.cm.automailer@gmail.com", "kalko.ev@gmail.com", "Coin test 001", coin.toString());
    }

    private void processCoinPrice(Coin coin) throws CmGenericException {
        if (coin.getPrice() != null) {
            LOGGER.debug("Price for coin has been successfully estimated");
            return;
        }

        if (coin.getRawPrice() != null) {
            calculateByGrade(coin);
            LOGGER.debug("Price for coin has been successfully estimated");
        } else {
            processCoinWithoutPrice(coin);
            LOGGER.debug("Cannot estimate price for coin");
        }
    }

    private void processCoinWithoutPrice(Coin coin) {

    }

    private void calculateByGrade(Coin coin) throws CmGenericException {
        BigDecimal rawPrice = coin.getRawPrice();
        BigDecimal multiplier = GradePriceFileReader.getInstance().getGradeMultiplier(coin.getGrade());
        BigDecimal price = rawPrice.multiply(multiplier);

        coin.setPrice(price);
    }

    private void checkCoinParameters(Coin coin) throws CmGenericException {
        if(coin.getComposition() == null) {
            LOGGER.warn("An error occurred during processing new coin - required field 'Composition' is missing. Skipping...");
            throw new CmGenericException();
        } else if(coin.getCountry().isEmpty()) {
            LOGGER.warn("An error occurred during processing new coin - required field 'Country' is missing. Skipping...");
            throw new CmGenericException();
        } else if(coin.getYear() == 0L) {
            LOGGER.warn("An error occurred during processing new coin - required field 'Year' is missing. Skipping...");
            throw new CmGenericException();
        } else if(coin.getGrade() == null) {
            LOGGER.warn("An error occurred during processing new coin - required field 'Grade' is missing. Skipping...");
            throw new CmGenericException();
        }
    }

    private void saveCoin(Coin coin) {
        coinRepositoryJPA.create(coin);
    }
}
