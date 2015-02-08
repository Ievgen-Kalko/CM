package com.cm.helpers;

import com.cm.domain.model.Coin;
import org.drools.KnowledgeBase;
import org.drools.runtime.StatelessKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class CoinPriceRuleHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinPriceRuleHelper.class);

    private StatelessKnowledgeSession coinsKSession;

    private KnowledgeBase coinsKBase;

    public CoinPriceRuleHelper() {
    }

    /**
     * Execute rule (if any) linked with (@link coin)
     * @param coin
     */
    public void execute(Coin coin) {
        Assert.notNull(coin, "method was invoked with null arg");

        LOGGER.debug("Starting executing rule for calculation coin's price");

        coinsKSession.execute(coin);

        LOGGER.debug("Finishing executing rule for calculation coin's price");
    }

    public void setCoinsKSession(StatelessKnowledgeSession coinsKSession) {
        this.coinsKSession = coinsKSession;
    }

    public void setCoinsKBase(KnowledgeBase coinsKBase) {
        this.coinsKBase = coinsKBase;
    }

    public StatelessKnowledgeSession getCoinsKSession() {

        return coinsKSession;
    }

    public KnowledgeBase getCoinsKBase() {
        return coinsKBase;
    }
}

