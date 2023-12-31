// package utils;

// import org.apache.poi.ss.usermodel.*;
// import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.ss.usermodel.WorkbookFactory;
// import java.io.FileInputStream;
// import java.io.IOException;
// import java.util.*;

// public class excelReadFile {
//     public static Map<String, String> readTestData(String excelFilePath, String sheetName) throws IOException {
//         try (FileInputStream fileInputStream = new FileInputStream(excelFilePath);
//              Workbook workbook = WorkbookFactory.create(fileInputStream)) {

//             Sheet sheet = workbook.getSheet(sheetName);
//             Row headerRow = sheet.getRow(0); // Assuming header row is at index 0

//             int columnCount = headerRow.getLastCellNum();
//             Map<String, String> testDataMap = new HashMap<>();

//             for (int j = 0; j < columnCount; j++) {
//                 Cell headerCell = headerRow.getCell(j);
//                 String headerName = getStringCellValue(headerCell);

//                 // Initialize the map with header names as keys
//                 testDataMap.put(headerName, "");
//             }

//             int rowCount = sheet.getLastRowNum();

//             for (int i = 1; i <= rowCount; i++) {
//                 Row row = sheet.getRow(i);

//                 for (int j = 0; j < columnCount; j++) {
//                     Cell headerCell = headerRow.getCell(j);
//                     String headerName = getStringCellValue(headerCell);

//                     Cell cell = row.getCell(j);
//                     String cellValue = getStringCellValue(cell);

//                     // Update the map with data values
//                     testDataMap.put(headerName, cellValue);
//                 }
//             }

//             return testDataMap;
//         }
//     }

//     private static String getStringCellValue(Cell cell) {
//         if (cell == null) {
//             return "";
//         }
//         switch (cell.getCellType()) {
//             case STRING:
//                 return cell.getStringCellValue();
//             case NUMERIC:
//                 return String.valueOf(cell.getNumericCellValue());
//             default:
//                 return "";
//         }
//     }
// }


package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class excelReadFile {
    public static Map<String, String> readTestData(String excelFilePath, String sheetName) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(excelFilePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0); // Assuming header row is at index 0

            int columnCount = headerRow.getLastCellNum();
            Map<String, String> testDataMap = new HashMap<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                for (int j = 0; j < columnCount; j++) {
                    String headerName = getStringCellValue(headerRow, j);
                    String cellValue = getStringCellValue(row, j);

                    // Construct a key based on the header name and row number
                    String key = headerName + "_Row" + (i + 1);

                    // Update the map with data values
                    testDataMap.put(key, cellValue);
                }
            }

            return testDataMap;
        }
    }

    private static String getStringCellValue(Row row, int columnIndex) {
        if (row == null) {
            return "";
        }

        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            default:
                return "";
        }
    }
}
