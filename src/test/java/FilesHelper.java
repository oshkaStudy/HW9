import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class FilesHelper {

    public void checkCsvRow(InputStream is, int rowIndex, String ... expectedRowValues) throws Exception {

        try (CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

            List<String[]> data = csvReader.readAll();

            assertThat(data)
                    .withFailMessage("CSV-файл содержит меньше строк, чем ожидалось. З" +
                            "апрашиваемая строка: %s", rowIndex)
                    .hasSizeGreaterThan(rowIndex);

            assertThat(data.get(rowIndex))
                    .withFailMessage("Строка %s не совпадает с ожидаемой", rowIndex)
                    .containsExactly(expectedRowValues);
        }
    }

    public void checkPdfText(InputStream is, String expectedText) throws Exception {

        PDF pdf = new PDF(is);

        assertThat(pdf.text)
                .withFailMessage("Искомый текст не найден в документе: %s",
                        expectedText)
                .contains(expectedText);

    }

    public void checkXlsCell(InputStream is, int Sheet, int Row, int Cell, String expectedCellValue) throws Exception {

        XLS xls = new XLS(is);

        String actualCellValue = xls.excel.getSheetAt(Sheet).getRow(Row).getCell(Cell).getStringCellValue();

        assertThat(actualCellValue)
                .withFailMessage("Значение ячейки: %s, вместо ожидаемого %s",
                        actualCellValue, expectedCellValue)
                .contains(expectedCellValue);

    }

}
