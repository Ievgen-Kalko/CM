package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.helpers.CoinPriceRuleHelper;
import com.cm.helpers.EmailHelper;
import com.cm.persistence.jpa.CoinRepositoryJPA;
import com.cm.service.impl.CoinServiceImpl;
import com.cm.util.CmGenericException;
import com.cm.util.GradePriceFileReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static com.sun.javaws.JnlpxArgs.verify;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Mockito.when;

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
    public void whenCheckCoinParametersInvokedWithNullArg_thenExceptionShouldBeThrown() throws CmGenericException {
        coinService.checkCoinParameters(null);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingComposition_thenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingCountry_thenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingYear_thenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = CmGenericException.class)
    public void whenCheckCoinParametersInvokedWithMissingGrade_thenExceptionShouldBeThrown() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);

        coinService.checkCoinParameters(coin);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenCalculatePriceInvokedWithNullArg_thenExceptionShouldBeThrown() throws CmGenericException {
        coinService.calculatePrice(null);
    }

    @Test
    public void whenCalculatePriceInvokedWithAlreadyExistedPrice_thenPriceNotChangedAndReturnTrue() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setPrice(new BigDecimal(12));

        boolean expectedStatus = true;
        boolean actualStatus = coinService.calculatePrice(coin);

        BigDecimal expectedPrice = new BigDecimal(12);
        BigDecimal actualPrice = coin.getPrice();

        assertTrue(expectedStatus == actualStatus);
        assertTrue(expectedPrice.compareTo(actualPrice) == 0);
    }

    @Test
    public void whenCalculatePriceInvokedWithNonExistedPriceButWithExistedRawPrice_thenCalculateByGradeInvoked() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setRawPrice(new BigDecimal(23));

        boolean expectedStatus = true;
        boolean actualStatus = coinService.calculatePrice(coin);

        assertTrue(expectedStatus == actualStatus);
        assertNotNull(coin.getPrice());
    }

    @Test
    public void whenCalculatePriceInvokedWithNonExistedPriceAndRawPrice_thenFalseReturnedAndPriceIsNull() throws CmGenericException {
        Coin coin = new Coin();
        coin.setComposition(Coin.CompositionType.GOLD);
        coin.setCountry("UK");
        coin.setYear(1990);
        coin.setCirculation(23);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);

        boolean expectedStatus = false;
        boolean actualStatus = coinService.calculatePrice(coin);

        assertTrue(expectedStatus == actualStatus);
        assertNull(coin.getPrice());
    }
}
