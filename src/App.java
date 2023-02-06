import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        sign();

    }

    static String heroname;

    public static void sign() throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println(
                "***********************************************************************************************************************************************");
        System.out.println("\t\t\t\tWelcome");
        System.out.println("\n\t\t\t\t1.Signup");
        System.out.println("\n\t\t\t\t2.Login");
        System.out.println(
                "***********************************************************************************************************************************************");

        int reg = input.nextInt();
        switch (reg) {
            case 1:
                UserProfile.signup();
                break;
            case 2:
                UserProfile.Loginvalidations();
                break;
            default:
                System.out.println("Enter valid option\n\n");
                sign();
        }

    }

    public static void mainpage(String player) throws FileNotFoundException, IOException {
        heroname = player;
        System.out.println(
                "...........................................................................................................");
        System.out.println("Welcome: " + player);
        System.out.println("\n\t\t\t\t1.Play Game");
        System.out.println("\n\t\t\t\t2.Leaderbard");
        System.out.println("\n\t\t\t\t3.Delete my account");
        System.out.println("\n\t\t\t\t4.Exit");
        System.out.println(
                "...........................................................................................................");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                DamageDealt.Play();
                break;
            case 2:
                ScoreView.getLeaderboard();
                break;
            case 3:
                UserProfile.delete(heroname);
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Enter valid option\n\n");
                mainpage(player);
        }
    }
}
