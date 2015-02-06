package com.cm.processors;

import com.cm.domain.model.Coin;
import com.cm.helpers.CoinPriceRuleHelper;
import com.cm.helpers.CoinsFileReader;
import com.cm.service.CoinService;
import com.cm.util.CmGenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.net.URL;
import java.util.List;

@Component("mainProcessor")
public class MainProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainProcessor.class);

    private final static String INPUT_FILES_PATH = "./cm-core/src/main/resources/inbox";
    private final static String OUTPUT_FILES_PATH = "./cm-core/src/main/resources/outbox/";
    private final static String ERROR_FILES_PATH = "./cm-core/src/main/resources/error/";
    private final static long WAITING_INTERVAL = 3000;

    @Autowired
    private CoinProcessor coinProcessor;

    @Autowired
    private FileProcessor fileProcessor;

    @Autowired
    private CoinsFileReader coinsFileReader;

    public static void main(String[] args) {
        LOGGER.info("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MainProcessor mainProcessor = (MainProcessor) context.getBean("mainProcessor");

        mainProcessor.start();
    }

    public void start() {
        while(true) {
            List<File> files = fileProcessor.getFiles(INPUT_FILES_PATH);

            if(files.size() > 0) {
                for(File file : files) {
                    try {
                        coinProcessor.processNewCoin(coinsFileReader.unmarshall(file));
                        fileProcessor.moveFile(file, OUTPUT_FILES_PATH);
                    } catch (CmGenericException e) {
                        fileProcessor.moveFile(file, ERROR_FILES_PATH);
                        LOGGER.error("Failed to process coin from file [" + file.getName() + "]");
                    }
                }
            }

            try {
                Thread.sleep(WAITING_INTERVAL);
                LOGGER.debug("Searching for incoming files...");
            } catch (InterruptedException e) {
                LOGGER.debug("Interrupted exception occurred.");
            }
        }
    }
}
