package com.cm.processors;

import com.cm.helpers.CoinsFileReader;
import com.cm.util.CmGenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Component("mainProcessor")
@Transactional
public class MainProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainProcessor.class);

    private final static long WAITING_INTERVAL = 10000;

    @Autowired
    private CoinProcessor coinProcessor;

    @Autowired
    private FileProcessor fileProcessor;

    @Autowired
    private CoinsFileReader coinsFileReader;

    public MainProcessor() {
    }

    public static void main(String[] args) {
        LOGGER.info("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MainProcessor mainProcessor = (MainProcessor) context.getBean("mainProcessor");

        mainProcessor.start();
    }

    public void start() {
        fileProcessor.createDirectories();;

        while(true) {
            try {
                List<File> files = fileProcessor.getFiles();

                if (files.size() > 0) {
                    for (File file : files) {
                        try {
                            coinProcessor.processNewCoin(coinsFileReader.unmarshall(file));
                            fileProcessor.moveFileToOutboxDir(file);
                        } catch (CmGenericException e) {
                            fileProcessor.moveFileToErrorDir(file);
                            LOGGER.error("Failed to process coin from file [" + file.getName() + "]");
                        }
                    }
                }
            } catch (CmGenericException e) {
                LOGGER.error("Failed to read files from inbox folder or move files to error folder");
            }

            try {
                LOGGER.debug("Searching for incoming files...");
                Thread.sleep(WAITING_INTERVAL);
            } catch (InterruptedException e) {
                LOGGER.debug("Interrupted exception occurred.");
            }
        }
    }
}
