package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.persistence.jpa.CoinRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("com.cm.service.coinService")
@Transactional
public class CoinService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinService.class);

    @Autowired
    private CoinRepositoryJPA coinRepositoryJPA;

    public CoinService() {
    }

    public void saveCoin(Coin coin) {
        coinRepositoryJPA.create(coin);
    }
}
