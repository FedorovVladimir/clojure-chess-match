package excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;

public class MyClass {
    public static void main(String[] args) {
        new MyClass().hi();
    }

    public MyClass() {
        System.out.println("create class yees!");
    }

    public void hi() {
        System.out.println("hi");
    }

    public void hello(String text) throws IOException {
        System.out.println(text);
        String path = "resources/excel/prev.xls";
        FileInputStream fileXls = new FileInputStream(path);
        Workbook workbook = new HSSFWorkbook(fileXls);
    }
}
