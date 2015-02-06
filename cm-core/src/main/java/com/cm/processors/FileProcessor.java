package com.cm.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component("com.cm.processors.FileProcessor")
public class FileProcessor {

    private final static Logger LOGGER = LoggerFactory.getLogger(FileProcessor.class);

    public FileProcessor() {
    }

    public List<File> getFiles(String path) {
        List<File> files = new ArrayList<>();

        try {
            Files.walk(Paths.get(path)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(filePath.toFile());
                }
            });
        } catch (IOException e) {
            LOGGER.error("Cannot read file.");
            e.printStackTrace();
        }

        return files;
    }

    public void moveFile(File file, String newPath) {
        try{
            boolean isMoved = file.renameTo(new File(newPath + file.getName()));

            if(!isMoved){
                LOGGER.info("Cannot move file [" + file.getName() + "] to [" + newPath + "].");
            }
        }catch(Exception e){
            LOGGER.warn("Error occurred while trying to move file [" + file.getName() + "] to [" + newPath + "].");
        }
    }
}
