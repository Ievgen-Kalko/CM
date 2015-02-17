package com.cm.processors;

import com.cm.util.CmGenericException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileProcessorTest {

    @InjectMocks
    private FileProcessor fileProcessor;

    @Mock
    private File fileStub;

    @Mock
    private File newFileStub;

    @Before
    public void init() {
        when(fileStub.getName()).thenReturn("name.xml");
        when(fileStub.getPath()).thenReturn("/somePath/name.xml");

        when(newFileStub.getName()).thenReturn("name.xml");
        when(newFileStub.getPath()).thenReturn("/someNewPath/name.xml");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstArgumentPassedToMoveFileIsNullThenExceptionIsThrown() throws CmGenericException {
        fileProcessor.moveFile(null, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSecondArgumentPassedToMoveFileIsNullThenExceptionIsThrown() throws CmGenericException {
        fileProcessor.moveFile(fileStub, null);
    }

    @Test
    public void whenFileMovedThenItsPathShouldBeChanged() throws CmGenericException {
        when(fileStub.renameTo(any(File.class))).thenReturn(true);

        String actualFileName = fileStub.getPath();
        String expectedFileName = "/somePath/name.xml";

        assertEquals(expectedFileName, actualFileName);

        String actualFileName2 = fileProcessor.moveFile(fileStub, "/someNewPath/").getPath();
        String expectedFileName2 = "\\someNewPath\\name.xml";

        assertEquals(expectedFileName2, actualFileName2);
    }

    @Test
    public void whenMoveToOutputDirInvokedThenFilePathShouldBeChanged() throws CmGenericException {
        when(fileStub.renameTo(any(File.class))).thenReturn(true);

        String actualFileName = fileStub.getPath();
        String expectedFileName = "/somePath/name.xml";

        assertEquals(expectedFileName, actualFileName);

        String actualFileName2 = fileProcessor.moveFileToOutboxDir(fileStub).getPath();
        String expectedFileName2 = ".\\outbox\\name.xml";

        assertEquals(expectedFileName2, actualFileName2);
    }

    @Test
    public void whenMoveToErrorDirInvokedThenFilePathShouldBeChanged() throws CmGenericException {
        when(fileStub.renameTo(any(File.class))).thenReturn(true);

        String actualFileName = fileStub.getPath();
        String expectedFileName = "/somePath/name.xml";

        assertEquals(expectedFileName, actualFileName);

        String actualFileName2 = fileProcessor.moveFileToErrorDir(fileStub).getPath();
        String expectedFileName2 = ".\\error\\name.xml";

        assertEquals(expectedFileName2, actualFileName2);
    }

    @Test
    public void whenMoveToProcessingDirInvokedThenFilePathShouldBeChanged() throws CmGenericException {
        when(fileStub.renameTo(any(File.class))).thenReturn(true);

        String actualFileName = fileStub.getPath();
        String expectedFileName = "/somePath/name.xml";

        assertEquals(expectedFileName, actualFileName);

        String actualFileName2 = fileProcessor.moveFileToProcessingDir(fileStub).getPath();
        String expectedFileName2 = ".\\processing\\name.xml";

        assertEquals(expectedFileName2, actualFileName2);
    }
}
