package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PrevList {
    public static void main(String[] args) {
        new PrevList().hi();
    }

    public PrevList() {
        System.out.println("create class yees!");
    }

    public void hi() {
        System.out.println("hi");
    }

    public void enterPrevList(String path) throws IOException {
        System.out.println(path);
        FileInputStream fileXls = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(fileXls);

        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < 200; i++) {
            Row rowNew = sheet.createRow(i);
            for (int j = 0; j < 30; j++) {
                if (i == 0 && j == 0) {
                    Cell cellNew = rowNew.createCell(j);
                    cellNew.setCellValue("Предварительный список");
                }

                if (i == 2 && j == 0){
                    Cell cellNew = rowNew.createCell(j);
                    cellNew.setCellValue("Название турнира");
                }
            }
        }
        //addMerged(sheet, 0,0, 0, 2);
        //addMerged(sheet, 2,2, 0, 2);
        writeFile(workbook, "prev.xls");
        try{
            Runtime.getRuntime().exec("cmd /c start " + path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void writeFile(Workbook workbook, String nameFile) throws IOException {
        FileOutputStream file = new FileOutputStream("C:\\Users\\Mikhail\\IdeaProjects\\ChessMatch\\src\\main\\resources\\excel\\" + nameFile);
        workbook.write(file);
        file.close();
    }

    public static void addMerged(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol){
        sheet.addMergedRegion(new CellRangeAddress(
                firstRow, //first row (0-based)
                lastRow, //last row (0-based)
                firstCol, //first column (0-based)
                lastCol //last column (0-based)
        ));
    }
}