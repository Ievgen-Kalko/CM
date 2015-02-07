package com.cm.service;

import com.cm.helpers.CoinPriceRuleHelper;
import com.cm.helpers.EmailHelper;
import com.cm.persistence.jpa.CoinRepositoryJPA;
import com.cm.service.impl.CoinServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

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

    @Test
    public void Test() {

    }
}
