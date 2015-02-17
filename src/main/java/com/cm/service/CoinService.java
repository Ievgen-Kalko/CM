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
     * Tries to calculate coin's price and returns ist status (i.e. true if calculated, otherwise - false)
     * @param coin
     * @return true if price has been successfully calculated, otherwise - false
     * @throws CmGenericException
     */
    boolean tryToCalculatePrice(Coin coin) throws CmGenericException;

    /**
     * Saves coin to the database
     * @param coin
     */
    void saveCoin(Coin coin);
}
