package com.cm.persistence.jpa.impl;

import com.cm.domain.model.Coin;
import com.cm.persistence.jpa.CoinRepositoryJPA;
import com.cm.persistence.jpa.EntityRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("com.cm.persistence.jpa.CoinRepositoryJPA")
@Transactional
public class CoinRepositoryJPAImpl extends EntityRepositoryJPA<Coin, Long> implements CoinRepositoryJPA {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinRepositoryJPAImpl.class);

    public CoinRepositoryJPAImpl() {
    }

    @Override
    protected Class getActualClass() {
        return Coin.class;
    }
}
