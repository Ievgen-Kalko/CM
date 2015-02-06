package com.cm.util;

import com.cm.domain.model.Coin;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

public class GradePriceFileReader {

    private final static String FILE_LOCATION = "rules/Grade_Price.properties";
    private static GradePriceFileReader instance = null;

    private InputStream inputStream;
    private Properties properties;

    private GradePriceFileReader() {
        this.properties = new Properties();
    }

    public static GradePriceFileReader getInstance() {
        if (instance == null) {
            instance = new GradePriceFileReader();
        }
        return instance;
    }

    public BigDecimal getGradeMultiplier(Coin.GradeType gradeType) throws CmGenericException {
        try {
            if(inputStream == null) {
                inputStream = getClass().getClassLoader().getResourceAsStream(FILE_LOCATION);
                properties.load(inputStream);
            }

            String rawMultiplier = properties.getProperty(gradeType.toString());

            if (rawMultiplier.isEmpty()) {
                throw new CmGenericException("Cannot find multiplier for Grade value " + gradeType.toString());
            }

            return new BigDecimal(rawMultiplier);

        } catch (IOException io) {
            throw new CmGenericException("Exception occurred when tried to read property file", io);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
