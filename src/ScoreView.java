import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ScoreView {
    int Oldscore;
    int Updatingscore;

    public static void savescore(int score, String user) throws FileNotFoundException, IOException {
        File file = new File("D:\\Java_projects\\RPG_GAME\\datafiles\\Book1.xlsx");
        XSSFWorkbook WK = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = WK.getSheetAt(0);
        int lastRow = sheet.getLastRowNum();
        for (int i = 0; i <= lastRow; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(2);

            if (cell1.getStringCellValue().equals(user)) {
                if (score > cell2.getNumericCellValue()) {
                    cell2.setCellValue(score);
                    System.out.println("\n\n\t\t\t\t##NEW HIGH SCORE " + score + "##");
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        WK.write(out);
                        out.close();

                    }
                    App.mainpage(user);
                    break;
                } else {
                    App.mainpage(user);
                }
            }

        }
    }

    public static void getLeaderboard() throws IOException {
        Scanner in = new Scanner(System.in);
        File file = new File("D:\\Java_projects\\RPG_GAME\\datafiles\\Book1.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        XSSFWorkbook wk = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = wk.getSheetAt(0);
        int lastrow = sheet.getLastRowNum();
        Map<String, Double> data = new HashMap<>();

        for (int i = 1; i <= lastrow; i++) {
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(2);

            String name = cell1.getStringCellValue();
            double score = cell2.getNumericCellValue();
            data.put(name, score);
        }
        LinkedHashMap<String, Double> sort = data.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue(),
                        (e1, e2) -> null,
                        () -> new LinkedHashMap<String, Double>()));
        System.out.println("\t\t\t\t\tLeaderboard\n");
        for (Map.Entry<String, Double> set : sort.entrySet()) {
            System.out.println("\t\t\t\t\t" + set.getKey() + "\t\t" + set.getValue());
        }
        String i = "0";
        while (i.equals("0")) {
            System.out.println("press 1 to exit");
            String j = in.nextLine();
            if (j.equals("1")) {
                i = "1";
            } else {
                i = "0";
            }
        }
        App.mainpage(App.heroname);
    }
}
