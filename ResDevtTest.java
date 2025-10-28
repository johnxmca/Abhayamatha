package RD;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.TestNG;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;


public class ResDevtTest {


    @Test
    void AA() throws IOException {
        JSONAssert.assertEquals("The JSON objects do not match as expected.",
                Files.readString(Paths.get("C:\\Users\\lenovo\\Desktop\\111.json")),
                "{\"name\":\"john\",\"value\":\"test\"}", true);

    }


    @Test
    void BB() {
        String question = "what is GME222";
        String abc = String.valueOf(new JSONObject().put("method", "message/send").put("id", 1).put("jsonrpc", "2.0")
                .put("params", new JSONObject().put("skill_id", "chat_with_gme").put("message", new JSONObject().put("role", "user")
                        .put("parts", new JSONArray().put(new JSONObject().put("text", question)))
                        .put("messageId", "abc123"))));
        System.out.println(abc);
    }


    @Test
    void CC() throws IOException {
        Files.writeString(Paths.get("C:\\Users\\lenovo\\Desktop\\3.txt"), "Kreupasanam Mama mary");
        Files.write(Paths.get("C:\\Users\\lenovo\\Desktop\\2.txt"), "Kreupasanam Mama mary".getBytes());

    }

    @Test
    void DD() throws IOException {
        TestNG testNG = new TestNG();
        testNG.setUseDefaultListeners(false);
        Iterator<Row> rowIterator = new XSSFWorkbook(new FileInputStream("D:\\JX-Imp\\RD-WC.xlsx")).getSheet("Sheet2").iterator();
        System.setOut(new PrintStream(new FileOutputStream("C:\\Users\\lenovo\\Desktop\\output.txt"))); // Redirect System.out to the file
        System.out.printf("| %-5s| %-26s| %-25s |%-26s | %-18s | %-95s | ", "Year", "Host", "Winner", " Runner-up", "Runner-up Score", "Result");
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> colIterator = row.cellIterator();
            int colCount = 1;
            while (colIterator.hasNext()) {
                Cell cell = colIterator.next();
                switch (colCount) {
                    case 1 -> System.out.printf("\n| %-4s ", (int) cell.getNumericCellValue());
                    case 2, 3, 4 -> System.out.printf("| %-25s ", cell.getStringCellValue());
                    case 5 -> {
                        if (cell.getCellType() == CellType.STRING)
                            System.out.printf("| %-18s ", cell.getStringCellValue());
                        if (cell.getCellType() == CellType.NUMERIC)
                            System.out.printf("| %-18s ", (int) cell.getNumericCellValue());
                    }
                    case 6 -> System.out.printf("| %-95s |", cell.getStringCellValue());
                }
                colCount++;
            }
        }
    }


    @Test
    void EE() {

        String str = "grammingrain~~";
        str.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> str.indexOf(c) == str.lastIndexOf(c))
                .findFirst()
                .ifPresentOrElse(
                        c -> System.out.println("First non-repeated: " + c),
                        () -> System.out.println("No non-repeated character")
                );

    }


}

