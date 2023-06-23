package com.demoqa.tests.HW10;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ZipTest {


    @Test
    void zipTest() throws Exception {
        try (ZipFile zf = new ZipFile(new File("src/test/resources/zip/file_sample.zip"))) {


            // Проверка первого файла "file_sample_csv.csv"
            ZipEntry entry1 = zf.getEntry("file_sample_csv.csv");
            assertNotNull(entry1, "entry1 не может быть null");
            try (InputStream inputStream = zf.getInputStream(entry1);

                 Reader reader = new InputStreamReader(inputStream)) {
                CSVReader csvReader = new CSVReader(reader);
                List<String[]> content = csvReader.readAll();

                Assertions.assertEquals(21416, content.size());

                final String[] firstRow = content.get(0);
                final String[] secondRow = content.get(1);
                final String[] thirdRow = content.get(2);

                Assertions.assertArrayEquals(new String[]{"Series_reference", "Period",
                        "Data_value", "Suppressed", "STATUS"}, firstRow);
                Assertions.assertArrayEquals(new String[]{"BDCQ.SEA1AA", "2011.06",
                        "80078", "", "F"}, secondRow);
                Assertions.assertArrayEquals(new String[]{"BDCQ.SEA1AA", "2011.09",
                        "78324", "", "F"}, thirdRow);
            }


            // Проверка второго файла "file_sample_pdf.pdf"
            ZipEntry entry2 = zf.getEntry("file_sample_pdf.pdf");
            assertNotNull(entry2, "entry2 не может быть null");
            try (InputStream inputStream = zf.getInputStream(entry2)) {
                PDF pdf = new PDF(inputStream);
                Assertions.assertEquals("Title_sample", pdf.title);
            }


            // Проверка третьего файла "file_sample_xls.xls"
            ZipEntry entry3 = zf.getEntry("file_sample_xls.xls");
            assertNotNull(entry3, "entry3 не может быть null");
            try (InputStream inputStream = zf.getInputStream(entry3)) {
                XLS xls = new XLS(inputStream);

                Assertions.assertEquals("United States",
                        xls.excel.getSheetAt(0).
                                getRow(1)
                                .getCell(4)
                                .getStringCellValue());
            }
        }
    }
}

