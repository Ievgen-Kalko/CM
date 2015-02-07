package com.cm.helpers;

import com.cm.domain.model.Coin;
import com.cm.util.CmGenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component("com.cm.helpers.CoinsFileReader")
public class CoinsFileReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(CoinsFileReader.class);

    public CoinsFileReader() {
    }

    public Coin unmarshall(File file) throws CmGenericException {
        Coin coin = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Coin.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            coin = (Coin) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            LOGGER.error("Cannot unmarshall file [" + file.getName() + "]");
            throw new CmGenericException("Cannot unmarshall file [" + file.getName() + "]", e);
        }

        return coin;
    }
}
