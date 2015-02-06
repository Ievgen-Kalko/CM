package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.persistence.jpa.CoinRepositoryJPA;
import com.cm.helpers.EmailHelper;
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
    private EmailHelper mailProcessor;

    public CoinService() {
    }

    public void checkCoinParameters(Coin coin) throws CmGenericException {
        Assert.notNull(coin, "method was invoked with null arg");

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

    public boolean calculatePrice(Coin coin) throws CmGenericException {
        Assert.notNull(coin, "method was invoked with null arg");

        priceRuleRunner.execute(coin);

        if (coin.getPrice() != null) {
            LOGGER.debug("Price for coin has been successfully estimated");

            return true;
        }

        if (coin.getRawPrice() != null) {
            calculateByGrade(coin);

            LOGGER.debug("Price for coin has been successfully estimated");

            return true;
        } else {
            LOGGER.debug("Cannot estimate price for coin");

            return false;
        }
    }

    public void processNewCoin(Coin coin) throws CmGenericException {
        Assert.notNull(coin, "method was invoked with null arg");

        checkCoinParameters(coin);

        priceRuleRunner.execute(coin);

        saveCoin(coin);

        mailProcessor.sendMail("com.cm.automailer@gmail.com", "kalko.ev@gmail.com", "Coin test 001", coin.toString());
    }

    private void calculateByGrade(Coin coin) throws CmGenericException {
        BigDecimal rawPrice = coin.getRawPrice();
        BigDecimal multiplier = GradePriceFileReader.getInstance().getGradeMultiplier(coin.getGrade());
        BigDecimal price = rawPrice.multiply(multiplier);

        coin.setPrice(price);
    }

    public void saveCoin(Coin coin) {
        coinRepositoryJPA.create(coin);
    }
}
