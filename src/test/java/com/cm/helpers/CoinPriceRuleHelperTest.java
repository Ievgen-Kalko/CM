package com.cm.helpers;

import com.cm.domain.model.Coin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class CoinPriceRuleHelperTest {

    @Autowired
    private CoinPriceRuleHelper ruleHelper;

    private Coin coin = new Coin();

    //Actually this is test for CoinPrice.xml rule, not for the class itself

    @Test
    public void whenUSGold1907VeryFineCoinReceivedThenPrice2750000() {
        coin.setCountry("US");
        coin.setYear(1907);
        coin.setGrade(Coin.GradeType.VERY_FINE);
        coin.setComposition(Coin.CompositionType.GOLD);

        ruleHelper.execute(coin);

        BigDecimal expectedPrice = new BigDecimal(2750000);
        BigDecimal actualPrice = coin.getPrice();

        assertTrue(expectedPrice.compareTo(actualPrice) == 0);
    }

    @Test
    public void whenRUOther1729VeryFineCoinReceivedThenPrice2500() {
        coin.setCountry("RU");
        coin.setYear(1729);
        coin.setGrade(Coin.GradeType.VERY_FINE);
        coin.setComposition(Coin.CompositionType.OTHER);

        ruleHelper.execute(coin);

        BigDecimal expectedPrice = new BigDecimal(2500);
        BigDecimal actualPrice = coin.getPrice();

        assertTrue(expectedPrice.compareTo(actualPrice) == 0);
    }

    @Test
    public void whenRUOther1728ExtraFineCoinReceivedThenPrice3200() {
        coin.setCountry("RU");
        coin.setYear(1728);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setComposition(Coin.CompositionType.OTHER);

        ruleHelper.execute(coin);

        BigDecimal expectedPrice = new BigDecimal(3200);
        BigDecimal actualPrice = coin.getPrice();

        assertTrue(expectedPrice.compareTo(actualPrice) == 0);
    }

    //Following test checks price calculation when additional Grade calculation is required (using Grade_Price.properties)
    //Assuming: GOOD=1.1, VERY_GOOD=1.2, FINE=1.5, VERY_FINE=1.65, EXTRA_FINE=2.5

    @Test
    public void whenUSSilver1123ExtraFineCoinReceivedThenPriceNotCalculated() {
        coin.setCountry("US");
        coin.setYear(1903);
        coin.setGrade(Coin.GradeType.EXTRA_FINE);
        coin.setComposition(Coin.CompositionType.SILVER);

        ruleHelper.execute(coin);

        assertNull(coin.getPrice());
    }

}
