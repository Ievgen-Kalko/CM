package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.util.CmGenericException;

public interface CoinService {

    void checkCoinParameters(Coin coin) throws CmGenericException;

    boolean calculatePrice(Coin coin) throws CmGenericException;

    void saveCoin(Coin coin);
}
