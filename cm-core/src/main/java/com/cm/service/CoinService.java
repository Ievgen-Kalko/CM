package com.cm.service;

import com.cm.domain.model.Coin;
import com.cm.util.CmGenericException;

public interface CoinService {

    /**
     * Checks if all (@link coin) has all the required parameters
     * @param coin
     * @throws CmGenericException
     */
    void checkCoinParameters(Coin coin) throws CmGenericException;

    /**
     * Tries to calculate coin's price
     * @param coin
     * @return
     * @throws CmGenericException
     */
    boolean calculatePrice(Coin coin) throws CmGenericException;

    /**
     * Saves coin to the database
     * @param coin
     */
    void saveCoin(Coin coin);
}
