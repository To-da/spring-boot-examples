package org.toda.boot.autoconfigure.poi.writer;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

public class PoiExcelWriter {

    private Logger logger = LoggerFactory.getLogger(PoiExcelWriter.class);

    /**
     *  rows in memory, exceeding rows will be flushed to disk
     */
    private int flushSize;

    public PoiExcelWriter(int flushSize) {
        this.flushSize = flushSize;
    }

    /**
     * could be interesting for spring batch
     *
     * @param inputResource source of the data read by lines
     * @param outputResource generated .xlsx resource
     */
    public void process(Resource inputResource, Resource outputResource) {
        SXSSFWorkbook wb = new SXSSFWorkbook(flushSize);
        try {
            wb.setCompressTempFiles(true); // temp files will be gzipped
            final Sheet sh = wb.createSheet();

            InputStreamReader is = new InputStreamReader(inputResource.getInputStream());
            BufferedReader br = new BufferedReader(is);
            final CreationHelper createHelper = sh.getWorkbook().getCreationHelper();

            br.lines().forEachOrdered(string -> createRow(string, sh, createHelper));

            FileOutputStream out = new FileOutputStream(outputResource.getFile());

            wb.write(out);
            IOUtils.closeQuietly(out);
        } catch (IOException e) {
           logger.error("IOE", e);
        } finally {
            if (wb != null) {
                // dispose of temporary files backing this workbook on disk
                wb.dispose();
            }
        }
    }

    protected void createRow(final String string, final Sheet sh, final CreationHelper createHelper) {
        createCell(sh).setCellValue(createHelper.createRichTextString(string));
    }

    protected Cell createCell(Sheet sh) {
        return creteRow(sh).createCell(1);
    }

    protected Row creteRow(Sheet sh) {
        return sh.createRow(sh.getPhysicalNumberOfRows());
    }

    public int getFlushSize() {
        return flushSize;
    }

    public void setFlushSize(int flushSize) {
        this.flushSize = flushSize;
    }
}
