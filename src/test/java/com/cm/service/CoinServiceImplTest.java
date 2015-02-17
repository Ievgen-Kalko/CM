package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.helpers.CoinPriceRuleHelper;
import com.cm.helpers.EmailHelper;
import com.cm.persistence.jpa.CoinRepositoryJPA;
import com.cm.service.impl.CoinServiceImpl;
import com.cm.util.CmGenericException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CoinServiceImplTest {

    @InjectMocks
    private CoinServiceImpl coinService;

    @Mock
    private CoinRepositoryJPA coinRepositoryJPA;

    @Mock
    private CoinPriceRuleHelper priceRuleRunner;

    @Mock
    private EmailHelper mailProcessor;

    @Test(expected = IllegalArgumentException.class)
    public void whenCheckCoinParametersInvokedWithNullArgThenExceptionShouldBeThrown() throws CmGenericException {
        coinService.checkCoinParameters(null);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingCompositionThenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingCountryThenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingYearThenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingGradeThenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalculatePriceInvokedWithNullArgThenExceptionShouldBeThrown() throws CmGenericException {
        coinService.tryToCalculatePrice(null);
    }

    @Test
    public void whenCalculatePriceInvokedWithAlreadyExistedPriceThenPriceNotChangedAndReturnTrue() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setPrice(new BigDecimal(12));

        boolean expectedStatus = true;
        boolean actualStatus = coinService.tryToCalculatePrice(coin);

        BigDecimal expectedPrice = new BigDecimal(12);
        BigDecimal actualPrice = coin.getPrice();

        assertTrue(expectedStatus == actualStatus);
        assertTrue(expectedPrice.compareTo(actualPrice) == 0);
    }

    @Test
    public void whenCalculatePriceInvokedWithNonExistedPriceButWithExistedRawPriceThenCalculateByGradeInvoked() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setRawPrice(new BigDecimal(23));

        boolean expectedStatus = true;
        boolean actualStatus = coinService.tryToCalculatePrice(coin);

        assertTrue(expectedStatus == actualStatus);
        assertNotNull(coin.getPrice());
    }

    @Test
    public void whenCalculatePriceInvokedWithNonExistedPriceAndRawPriceThenFalseReturnedAndPriceIsNull() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        boolean expectedStatus = false;
        boolean actualStatus = coinService.tryToCalculatePrice(coin);

        assertTrue(expectedStatus == actualStatus);
        assertNull(coin.getPrice());
    }
}
