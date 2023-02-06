import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class DamageDealt {
    static int enemydamagedealt = 20;
    static int herodamagedealt = 30;

    public static void Play() throws FileNotFoundException, IOException {
        int score = 0;
        int edefeated = 0;
        int hhealth = HealingPotion.herohealth;
        int  potions = HealingPotion.healingcount;
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        boolean running = true;
        System.out.println(
                "--------------------------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Dungeon");
        GAME: {
            while (running) {
                System.out.println(
                        "--------------------------------------------------------------------------------------------------------------------");
                int ehealth = HealingPotion.enemyhealth;
                System.out.println("\t#  Enemy has been appeared   #");
                while (ehealth > 0) {
                    System.out.println("\t Your HP: " + hhealth);
                    System.out.println("\t Enemy's HP: " + ehealth);
                    System.out.println("\n\t What would you like to do,");
                    System.out.println("\t1.Attack");
                    System.out.println("\t2.Drink healing potion");
                    System.out.println("\t3.Defend");
                    System.out.println(
                            "--------------------------------------------------------------------------------------------------------------------");

                    String opt = in.nextLine();
                    if (opt.equals("1")) {
                        int damagetaken = rand.nextInt(enemydamagedealt);
                        int damagegn = rand.nextInt(herodamagedealt);
                        score += damagegn * 0.2;
                        ehealth -= damagegn;
                        hhealth -= damagetaken;

                        System.out.println("You srike the enemy for " + damagegn + " damage");
                        System.out.println("You lost " + damagetaken + " health\n\n");
                        if (hhealth < 1) {
                            System.out.println("\n\t\t\t\tYou are too weak to continue");
                            System.out.println("\n\n\t\t\t\tYour Score: "+score+"\n");
                            ScoreView.savescore(score,App.heroname);
                            break;
                        }

                    }

                    else if (opt.equals("2")) {
                        if (potions > 0) {
                            hhealth += HealingPotion.Healingpercentage;
                            potions -= 1;
                            System.out.println("\t You drinked a healing potion . \n\t Your HP: " + hhealth +
                                    "\n\t You now have  " + potions + " left");
                            System.out.println("--------------------------------------------------------");

                        } else {
                            System.out.println(
                                    "\n\tYou have 0 healing potion left\n\tDefeat an enemy for a chance to get potion ");
                            System.out.println("--------------------------------------------------------");

                        }

                    }

                    else if (opt.equals("3")) {
                        int damagegn = rand.nextInt(herodamagedealt);
                        ehealth -= damagegn;
                        System.out.println("You srike the enemy for " + damagegn + " damage");
                        System.out.println("#You defended enemy attack#");
                        score += 3;
                        ehealth += rand.nextInt(30);

                    } else {
                        System.out.println("\tInvalid command!");
                    }
                }
                if (hhealth < 1) {
                    System.out.println("\n\t\t\t\tYou are too weak to continue");
                    ScoreView.savescore(score,App.heroname);
                    break;
                }
                System.out.println("--------------------------------------------------------");
                System.out.println("\t\t\t\tEnemy has been defeated\n\n");
                score += 75;
                edefeated += 1;
                System.out.println("Your score: " + score);
                System.out.println("Number of enemies defeated: " + edefeated + "\n\n");
                System.out.println("You have " + hhealth + " HP left");

                if (rand.nextInt(100) < HealingPotion.healingpotionchance) {
                    potions++;
                    System.out.println("The enemy dropped a healing potion");
                    System.out.println("\nYou now have " + potions + " healing potions");
                }
                System.out.println("");
                System.out.println("What would you like to do now ? ");
                System.out.println("1.Continue fighting");
                System.out.println("2.Exit dungeon\n\n");

                String input = in.nextLine();
                while (!input.equals("1") && !input.equals("2")) {
                    System.out.println("Invalid command");
                    input = in.nextLine();

                }
                if (input.equals("1")) {
                    System.out.println("Starting adventure");

                } else if (input.equals("2")) {
                    System.out.println("Exiting\n");
                    System.out.println("\n\t\t\t\t###################################################");
                    System.out.println("\t\t\t\t# Thanks for Playing #");
                    System.out.println("\t\t\t\t###################################################");
                    System.out.println("\n\n\t\t\t\tYour Score: "+score+"\n");
                    ScoreView.savescore(score,App.heroname);
                    break;
                }

            }
        }
    }
}
