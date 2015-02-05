package com.cm.persistence.jpa;

import com.cm.domain.model.Coin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("com.cm.persistence.jpa.CoinRepositoryJPA")
@Transactional
public class CoinRepositoryJPA extends EntityRepositoryJPA<Coin, Long> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinRepositoryJPA.class);

    @Override
    protected Class getActualClass() {
        return Coin.class;
    }
}
