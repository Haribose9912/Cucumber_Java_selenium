package Utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelDemo {
    public static void main(String[] args) {
        try {
            // Load the Excel file
            FileInputStream file = new FileInputStream(new File("src/main/resources/ContactDetails.xlsx"));
            Workbook workbook = WorkbookFactory.create(file);

            // Get the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows and cells
            for (Row row : sheet) {
                for (Cell cell : row) {
                    // Print cell value
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println(); // Move to the next row
            }

            // Close the workbook
            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        // write to excel
        
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Hello, Excel!");

        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/write.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
