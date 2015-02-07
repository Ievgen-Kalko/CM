package com.cm.helpers;

import com.cm.util.CmGenericException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoinsFileReaderTest {

    @InjectMocks
    private CoinsFileReader coinsFileReader;

    @Test(expected = IllegalArgumentException.class)
    public void whenGetSubscriptionsInvokedWithNullArg_thenExceptionIsThrown() throws CmGenericException {
        coinsFileReader.unmarshall(null);
    }
}
