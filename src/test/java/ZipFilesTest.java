import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class ZipFilesTest {

    private final ClassLoader cl = ZipFilesTest.class.getClassLoader();
    FilesHelper fh = new FilesHelper();

    //– Запаковать в zip архив несколько разных файлов - pdf, xlsx, csv
    //– Положить его в ресурсы
    //– Реализовать чтение и проверку содержимого каждого файла из архива

    @Test
    @DisplayName("Проверка на наличие всех трех типов файлов в архиве")
    void checkZipFileIncludeAllTypesFiles() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            boolean csvEntry = false, pdfEntry = false, xlsEntry = false;

            while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();

                if (name.endsWith(".csv")) {
                    csvEntry = true;
                }
                else if (name.endsWith(".pdf")) {
                    pdfEntry = true;
                }
                else if (name.endsWith(".xlsx")) {
                    xlsEntry = true;
                }
            }

            assertThat(csvEntry && pdfEntry && xlsEntry)
                    .withFailMessage("Не все типы файлов найдены в архиве." +
                            " CSV: %s, PDF: %s, XLS: %s",
                            csvEntry, pdfEntry, xlsEntry)
                    .isTrue();

        }
    }

    @Test
    @DisplayName("Проверка строки CSV-файла на соответствие")
    void csvFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            boolean csvEntry = false;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    csvEntry = true;

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> data = csvReader.readAll();

                    fh.checkCsvRow(data, 1, "JUnit 5", "https://junit.org");

                }
            }

            assertThat(csvEntry)
                    .withFailMessage("В архиве нет CSV-файла")
                    .isTrue();
        }
    }

    @Test
    @DisplayName("Проверка PDF-файла на наличие текста")
    void pdfFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            boolean pdfEntry = false;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    pdfEntry = true;
                    fh.checkPdfText(zis, "ИТОГО 2804.00");
                }
            }

            assertThat(pdfEntry)
                    .withFailMessage("В архиве нет PDF-файла")
                    .isTrue();
        }
    }

    @Test
    @DisplayName("Проверка значения ячейки CSV-файла")
    void xlsFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )) {
            ZipEntry entry;
            boolean xlsEntry = false;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    xlsEntry = true;
                    fh.checkXlsCell(zis, 1,5,1,"Километр;");
                }
            }

            assertThat(xlsEntry)
                    .withFailMessage("В архиве нет XLS-файла")
                    .isTrue();
        }
    }
}