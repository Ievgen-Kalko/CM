package com.cm.processors;

import com.cm.util.CmGenericException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component("com.cm.processors.FileProcessor")
@Transactional
public class FileProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileProcessor.class);

    public FileProcessor() {
    }

    public List<File> getFiles(String path) throws CmGenericException {
        Assert.notNull(path, "method was invoked with null arg");

        List<File> files = new ArrayList<>();

        try {
            Files.walk(Paths.get(path)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(filePath.toFile());
                }
            });
        } catch (IOException e) {
            LOGGER.error("Cannot read file.");
            throw new CmGenericException("Cannot read file", e);
        }

        return files;
    }

    public void moveFile(File file, String newPath) throws CmGenericException {
        Assert.notNull(file, "method was invoked with null arg");
        Assert.notNull(newPath, "method was invoked with null arg");

        try{
            boolean isMoved = file.renameTo(new File(newPath + file.getName()));

            if(!isMoved){
                LOGGER.info("Cannot move file [" + file.getName() + "] to [" + newPath + "].");
            }
        }catch(Exception e){
            LOGGER.warn("Error occurred while trying to move file [" + file.getName() + "] to [" + newPath + "].");
            throw new CmGenericException("Error occurred while trying to move file [" + file.getName() + "] to [" + newPath + "].", e);
        }
    }
}