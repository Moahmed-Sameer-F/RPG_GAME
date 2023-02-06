import java.io.*;
import java.util.Scanner;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class UserProfile {
  static String username;
  static String password;

  public static void signup() throws FileNotFoundException, IOException {
    Scanner reg = new Scanner(System.in);
    File file = new File("D:\\Java_projects\\RPG_GAME\\datafiles\\Book1.xlsx");
    XSSFWorkbook WK = new XSSFWorkbook(new FileInputStream(file));
    XSSFSheet sheet = WK.getSheetAt(0);
    int lastRow = sheet.getLastRowNum();
    System.out.println("Enter username:");
    String name = reg.nextLine();
    for (int i = 0; i <= lastRow; i++) {
      Row row = sheet.getRow(i);
      Cell cell = row.getCell(0);

      if (cell.getStringCellValue().equals(name) || name.equals("NAME")) {
        System.out.println("Usename already exist\n");
        signup();
        break;
      } else if (i == lastRow && (!cell.getStringCellValue().equals(name)) && !name.equals("NAME")) {

        System.out.println("Enter password:");
        String password = reg.nextLine();
        Row row1 = sheet.createRow(++lastRow);
        Cell cell1 = row1.createCell(0);
        Cell cell2 = row1.createCell(1);
        Cell cell3 = row1.createCell(2);
        cell1.setCellValue(name);
        cell2.setCellValue(password);
        cell3.setCellValue(0);
        try (FileOutputStream out = new FileOutputStream(file)) {
          WK.write(out);
          out.close();
          System.out.println("Thankyou for registering us");
          App.mainpage(name);
        }

        break;
      }
    }
  }

  static void Checkpassword(String pass) throws FileNotFoundException, IOException {
    File file = new File("D:\\Java_projects\\RPG_GAME\\datafiles\\Book1.xlsx");
    XSSFWorkbook WK = new XSSFWorkbook(new FileInputStream(file));
    XSSFSheet sheet = WK.getSheetAt(0);
    int lastRow = sheet.getLastRowNum();
    for (int i = 1; i <= lastRow; i++) {
      Row row = sheet.getRow(i);
      Cell cell1 = row.getCell(0);
      Cell cell2 = row.getCell(1);
      if (cell1.getStringCellValue().equals(username)) {
        if (pass.equals(cell2.getStringCellValue())) {
          App.mainpage(username);
          break;
        } else if (i == lastRow && !pass.equals(cell2.getStringCellValue())) {
          System.out.println("Password doesn't match\n\n");
          Loginvalidations();
        }
      }
    }
  }

  static void Loginvalidations() throws FileNotFoundException, IOException {
    Scanner input = new Scanner(System.in);
    File file = new File("D:\\Java_projects\\RPG_GAME\\datafiles\\Book1.xlsx");
    XSSFWorkbook WK = new XSSFWorkbook(new FileInputStream(file));
    XSSFSheet sheet = WK.getSheetAt(0);
    int lastRow = sheet.getLastRowNum();
    System.out.println("Enter usename: ");
    username = input.nextLine();
    for (int i = 0; i <= lastRow; i++) {
      Row row = sheet.getRow(i);
      Cell cell = row.getCell(0);

      if (cell.getStringCellValue().equals(username) && !cell.getStringCellValue().equals("NAME")) {
        System.out.println("Enter password:");
        password = input.nextLine();
        Checkpassword(password);
        break;
      } else if (i == lastRow
          && (!cell.getStringCellValue().equals(username) || cell.getStringCellValue().equals(username))) {
        System.out.println("Usename doesn't exist\n");
        Loginvalidations();
        break;
      }
    }

  }

  public static void delete(String name) throws FileNotFoundException, IOException {
    Scanner input = new Scanner(System.in);
    File file = new File("D:\\Java_projects\\RPG_GAME\\datafiles\\Book1.xlsx");
    XSSFWorkbook WK = new XSSFWorkbook(new FileInputStream(file));
    XSSFSheet sheet = WK.getSheetAt(0);
    int lastRow = sheet.getLastRowNum();
    for (int i = 1; i <= lastRow; i++) {
      Row row = sheet.getRow(i);
      Cell cell = row.getCell(0);
      Cell cell2 = row.getCell(1);
      if (cell.getStringCellValue().equals(username)) {
        System.out.println("Enter password:");
        password = input.nextLine();
        if (password.equals(cell2.getStringCellValue())) {
          System.out.println("press 1 to confirm");
          String yes = input.nextLine();
          if (yes.equals("1")) {
            removeRow(sheet, i);
            try (FileOutputStream out = new FileOutputStream(file)) {
              WK.write(out);
              out.close();
              System.out.println("Account deleted!");
            }
            App.sign();
            break;
          } else {
            System.out.println("Canceled");
            App.mainpage(App.heroname);
            break;

          }
        } else {
          System.out.println("Password doesn't match\n\n");
          App.mainpage(App.heroname);
          break;
        }
      }
    }
  }

  public static void removeRow(XSSFSheet sheet, int rowIndex) {
    int lastRowNum = sheet.getLastRowNum();
    if (rowIndex >= 0 && rowIndex < lastRowNum) {
      sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
    }
    if (rowIndex == lastRowNum) {
      Row removingRow = sheet.getRow(rowIndex);
      if (removingRow != null) {
        sheet.removeRow(removingRow);
      }
    }
  }

}
