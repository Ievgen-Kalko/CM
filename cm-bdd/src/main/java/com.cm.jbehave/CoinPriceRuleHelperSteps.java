package com.cm.jbehave;

import com.cm.domain.model.Coin;
import com.cm.helpers.CoinPriceRuleHelper;
import junit.framework.Assert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class CoinPriceRuleHelperSteps {

    private Coin coin;

    @Autowired
    private CoinPriceRuleHelper ruleHelper;

    @Given("an empty coin")
    public void anEmptyCoin() {
        this.coin = new Coin();
    }

    @When("its parameter $param is $value")
    public void aParameterIsSet(String param, String value) {

        if(param.equalsIgnoreCase(Coin.Columns.COMPOSITION.toString())) {
            this.coin.setComposition(Coin.CompositionType.valueOf(value));
            return;
        } else if(param.equalsIgnoreCase(Coin.Columns.COUNTRY.toString())) {
            this.coin.setCountry(value);
            return;
        } else if(param.equalsIgnoreCase(Coin.Columns.YEAR.toString())) {
            this.coin.setYear(Long.parseLong(value));
            return;
        } else if(param.equalsIgnoreCase(Coin.Columns.CIRCULATION.toString())) {
            this.coin.setCirculation(Long.parseLong(value));
            return;
        } else if(param.equalsIgnoreCase(Coin.Columns.GRADE.toString())) {
            this.coin.setGrade(Coin.GradeType.valueOf(value));
            return;
        } else if(param.equalsIgnoreCase(Coin.Columns.DESCRIPTION.toString())) {
            this.coin.setDescription(value);
            return;
        } else {
            throw new IllegalArgumentException("cannot find field " + param + " in Coin entity");
        }
    }

    @When("the price is calculated")
    public void thePriceIsCalculated() {
        ruleHelper.execute(this.coin);
    }

    @Then("the price should be $value")
    public void thePriceShouldBe(String value) {
        BigDecimal expected = new BigDecimal(value);
        BigDecimal actual = this.coin.getPrice();

        assertTrue(expected.compareTo(actual) == 0);
    }
}
