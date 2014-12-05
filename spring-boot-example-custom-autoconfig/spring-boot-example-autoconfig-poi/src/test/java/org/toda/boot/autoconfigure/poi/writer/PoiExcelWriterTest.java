package org.toda.boot.autoconfigure.poi.writer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PoiExcelWriterTest {

    private static final int ROWS_TO_GENERATE = 500;
    File sourceFile;
    File destination_file;

    @Before
    public void createTestFile() throws Exception {
        destination_file = File.createTempFile("test-poi-spring-boot", ".xlsx");
        destination_file.createNewFile();

        sourceFile = File.createTempFile("test-poi-spring-boot-source", ".txt");
        sourceFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(sourceFile);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < ROWS_TO_GENERATE; i++) {
            bw.write(UUID.randomUUID().toString());
            bw.newLine();
        }

        bw.close();

        destination_file.deleteOnExit();
        sourceFile.deleteOnExit();
    }

    @Test
    public void testProcess() throws Exception {
        PoiExcelWriter poiExcelWriter = new PoiExcelWriter(20);
        final Resource fileSystemResource = new FileSystemResource(destination_file);
        poiExcelWriter.process(new FileSystemResource(sourceFile), fileSystemResource);

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileSystemResource.getInputStream());
        final XSSFSheet sheetAt = xssfWorkbook.getSheetAt(0);

        Assert.assertThat("result file is empty", destination_file.length(), not(0l));
        Assert.assertThat(sheetAt.getPhysicalNumberOfRows(), is(ROWS_TO_GENERATE));
    }
}