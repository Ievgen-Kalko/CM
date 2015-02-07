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

    private final static String INPUT_FILES_PATH = "./inbox";
    private final static String OUTPUT_FILES_PATH = "./outbox/";
    private final static String ERROR_FILES_PATH = "./error/";
    private final static String PROCESSING_FILES_PATH = "./processing/";

    public FileProcessor() {
    }

    public List<File> getFiles() throws CmGenericException {
        Assert.notNull(INPUT_FILES_PATH, "method was invoked with null arg");

        List<File> files = new ArrayList<>();

        try {
            Files.walk(Paths.get(INPUT_FILES_PATH)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    files.add(filePath.toFile());
                }
            });
        } catch (IOException e) {
            LOGGER.error("Cannot read file.", e);
            throw new CmGenericException("Cannot read file", e);
        }

        return files;
    }

    public File moveFile(File file, String newPath) throws CmGenericException {
        Assert.notNull(file, "method was invoked with null arg");
        Assert.notNull(newPath, "method was invoked with null arg");

        try{
            File newFile = new File(newPath + file.getName());
            while(newFile.exists()) {
                newFile = renameFile(newFile);
            }

            boolean isMoved = file.renameTo(newFile);

            if(!isMoved){
                LOGGER.info("Cannot move file [" + file.getName() + "] to [" + newPath + "].");
                throw new CmGenericException("Error occurred while trying to move file [" + file.getName() + "] to [" + newPath + "].");
            }

            return newFile;

        }catch(Exception e){
            LOGGER.warn("Error occurred while trying to move file [" + file.getName() + "] to [" + newPath + "].", e);
            throw new CmGenericException("Error occurred while trying to move file [" + file.getName() + "] to [" + newPath + "].", e);
        }
    }

    private File renameFile(File file) {
        String oldName = file.getName();
        String newName;
        int index = oldName.lastIndexOf(".");
        int pathIndex = file.getPath().indexOf(oldName);
        newName = file.getPath().substring(0, pathIndex) + oldName.substring(0, index) + "1" + oldName.substring(index, oldName.length());
        return new File(newName);
    }

    public File moveFileToOutboxDir(File file) throws CmGenericException {
        return moveFile(file, OUTPUT_FILES_PATH);
    }

    public File moveFileToErrorDir(File file) throws CmGenericException {
        return moveFile(file, ERROR_FILES_PATH);
    }

    public File moveFileToProcessingDir(File file) throws CmGenericException {
        return moveFile(file, PROCESSING_FILES_PATH);
    }

    public void createDirectories() {
        List<File> directories = new ArrayList<File>(4);
        directories.add(new File(INPUT_FILES_PATH));
        directories.add(new File(OUTPUT_FILES_PATH));
        directories.add(new File(ERROR_FILES_PATH));
        directories.add(new File(PROCESSING_FILES_PATH));

        for(File dir : directories) {
            if(!dir.exists()) {
                dir.mkdir();
            }
        }
    }
}
