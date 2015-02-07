package com.cm.processors;

import com.cm.util.CmGenericException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessorTest {

    @InjectMocks
    private FileProcessor fileProcessor;

    @Test(expected = IllegalArgumentException.class)
    public void whenNullArgumentPassedToGetFiles_thenExceptionIsThrown() throws CmGenericException {
        fileProcessor.getFiles(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstArgumentPassedToMoveFileIsNull_thenExceptionIsThrown() throws CmGenericException {
        fileProcessor.moveFile(null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSecondArgumentPassedToMoveFileIsNull_thenExceptionIsThrown() throws CmGenericException {
        fileProcessor.moveFile(new File(""), null);
    }

}
