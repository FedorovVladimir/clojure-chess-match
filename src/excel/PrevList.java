package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static org.apache.poi.hssf.record.ExtendedFormatRecord.THIN;

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

    private void enterPrevList(String path, List<Map<String, String>> base, int size, String fileName) throws IOException {
        System.out.println(path);
        FileInputStream fileXls = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(fileXls);
        Sheet sheet = workbook.getSheetAt(0);
        if (fileName == "prev")
            textPrevList(workbook, sheet, base, size);
        else
            textStartList(workbook, sheet, base, size);
        writeFile(workbook, fileName);
        try{
            Runtime.getRuntime().exec("cmd /c start " + path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void textPrevList(Workbook workbook, Sheet sheet, List<Map<String, String>> base, int size) throws IOException {
        int number = 1;
        int stringList = 0;
        for (int i = 0; i < (size + 3); i++) {
            Row rowNew = sheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 1) {
                    Cell cellNew = rowNew.createCell(j);
                    cellNew.setCellValue("Предварительный");
                }
                if (i == 0 && j == 2) {
                    Cell cellNew = rowNew.createCell(j);
                    cellNew.setCellValue("список");
                }
                if (i == 2){
                    Cell cellNew = rowNew.createCell(j);
                    setBorder(workbook, cellNew);
                    if (j == 0){
                        cellNew.setCellValue("№");
                    }
                    if (j == 1) {
                        cellNew.setCellValue("Фамилия");
                    }
                    if (j == 2) {
                        cellNew.setCellValue("Имя");
                    }
                    if (j == 3) {
                        cellNew.setCellValue("Отчество");
                    }
                    if (j == 4) {
                        cellNew.setCellValue("Рейтинг");
                    }
                }
                if (i > 2 && stringList < size){
                    Cell cellNew = rowNew.createCell(j);
                    setBorder(workbook, cellNew);
                    if (j == 0){
                        cellNew.setCellValue(number);
                    }
                    if (j == 1) {
                        cellNew.setCellValue(base.get(stringList).get("last"));
                    }
                    if (j == 2) {
                        cellNew.setCellValue(base.get(stringList).get("first"));
                    }
                    if (j == 3) {
                        cellNew.setCellValue(base.get(stringList).get("patro"));
                    }
                    if (j == 4) {
                        cellNew.setCellValue(String.valueOf(base.get(stringList).get("rating_rus")));
                    }
                    sheet.autoSizeColumn(j);
                }
            }
            if (i > 2){
                stringList++;
                number++;
            }
        }
    }

    private void textStartList(Workbook workbook, Sheet sheet, List<Map<String, String>> base, int size) throws IOException {
        int number = 1;
        int stringList = 0;
        for (int i = 0; i < (size + 3); i++) {
            Row rowNew = sheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                if (i == 0 && j == 1) {
                    Cell cellNew = rowNew.createCell(j);
                    cellNew.setCellValue("Стартовый");
                }
                if (i == 0 && j == 2) {
                    Cell cellNew = rowNew.createCell(j);
                    cellNew.setCellValue("лист");
                }
                if (i == 2){
                    Cell cellNew = rowNew.createCell(j);
                    setBorder(workbook, cellNew);
                    if (j == 0){
                        cellNew.setCellValue("№");
                    }
                    if (j == 1) {
                        cellNew.setCellValue("Фамилия");
                    }
                    if (j == 2) {
                        cellNew.setCellValue("Имя");
                    }
                    if (j == 3) {
                        cellNew.setCellValue("Отчество");
                    }
                    if (j == 4) {
                        cellNew.setCellValue("Рейтинг");
                    }
                }
                if (i > 2 && stringList < size){
                    Cell cellNew = rowNew.createCell(j);
                    setBorder(workbook, cellNew);
                    if (j == 0){
                        cellNew.setCellValue(number);
                    }
                    if (j == 1) {
                        cellNew.setCellValue(base.get(stringList).get("last"));
                    }
                    if (j == 2) {
                        cellNew.setCellValue(base.get(stringList).get("first"));
                    }
                    if (j == 3) {
                        cellNew.setCellValue(base.get(stringList).get("patro"));
                    }
                    if (j == 4) {
                        cellNew.setCellValue(String.valueOf(base.get(stringList).get("rating_rus")));
                    }
                    sheet.autoSizeColumn(j);
                }
            }
            if (i > 2){
                stringList++;
                number++;
            }
        }
    }

    private void setBorder(Workbook workbookTemplate, Cell cell) throws IOException {
        Sheet sheet = workbookTemplate.getSheetAt(0);
        CellStyle style = workbookTemplate.createCellStyle();
        style.setBorderBottom(BorderStyle.valueOf(THIN));
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.valueOf(THIN));
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.valueOf(THIN));
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.valueOf(THIN));
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);
        writeFile(workbookTemplate, "prev.xls");
    }

    public static void writeFile(Workbook workbook, String nameFile) throws IOException {
        FileOutputStream file = new FileOutputStream("resources/excel/"+ nameFile);
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



    public List<Map<String, String>> EnterPrevListDataBase(List<Map<String, String>> base, String path, String fileName) throws IOException {
        List<Map<String, String>> list = getListFromClojure(base);
        System.out.println(list);
        System.out.println(list.size());
        enterPrevList(path, list, list.size(), fileName);
        return list;
    }

    private List<Map<String, String>> getListFromClojure(List<Map<String, String>> base) {
        List<Map<String, String>> list = new LinkedList<>();
        for (Map<String, String> row : base) {
            Map<String, String> map = new HashMap<>();
            Set<String> keys = row.keySet();
            Collection<String> values = row.values();
            Iterator<String> iteratorK = keys.iterator();
            Iterator<String> iteratorV = values.iterator();
            for (int i = 0; i < keys.size(); i++) {
                String key = String.valueOf(iteratorK.next());
                map.put(key.replace(":", ""), iteratorV.next());
            }
            list.add(map);
        }
        return list;
    }
}