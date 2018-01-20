/**
 * @author James O'Rourke_20074556 on 17/01/2018
 */

import java.util.Scanner;
public class Problem110102_old {
    //declare the table
    private String[][] table;
    private int bombs;
    Scanner input;
    private int height;
    private int width;

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Problem110102_old app = new Problem110102_old();
    }

    public Problem110102_old(){
        input = new Scanner(System.in);
        homeMenu();
    }

    private void exitApp() {

        System.out.println("Exiting App");
        System.exit(0);
    }
    private String home() {
        System.out.println("");
        System.out.println("__________________");
        System.out.println("¦   Run(r)       ¦");
        System.out.println("__________________");
        System.out.println("     ---------     ");
        System.out.println("   e) Exit");
        System.out.print("==>> ");
        String option = input.next();
        option = option.toLowerCase();
        return option;
    }
    private void homeMenu() {
        String option = home();
        while (!option.equals("e")) {
            switch (option) {
                case "r":
                    run();
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            // pause the program so that the user can read what we just printed
            // to the terminal window
            System.out.println("\nPress Return to continue...");
            input.nextLine();
            input.nextLine();
            option = home();
        }
        exitApp();
    }

    private String diff() {
        System.out.println("");
        System.out.println("___________________");
        System.out.println("¦     Easy  (a)   ¦");
        System.out.println("¦   Medium  (b)   ¦");
        System.out.println("¦     Hard  (c)   ¦");
        System.out.println("¦  Go Back  (g)   ¦");
        System.out.println("__________________");
        System.out.println("     ---------     ");
        System.out.println("   e) Exit");
        System.out.print("==>> ");
        String option = input.next();
        option = option.toLowerCase();
        return option;
    }


    private void difMenu() {
        String option = diff();
        while (!option.equals("e")) {
            switch (option) {
                case "a":
                    bombs = 3;
                    random();
                    break;
                case "b":
                    bombs = 5;
                    random();
                    break;
                case "c":
                    bombs = 7;
                    random();
                    break;
                case "x":
                    //testing
                    int k =(table.length* table[0].length);
                    System.out.println(k);
                    break;
                case "g":
                    run();
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }
            // pause the program so that the user can read what we just printed
            // to the terminal window
            System.out.println("\nPress Return to continue...");
            input.nextLine();
            input.nextLine();
            option = diff();
        }
        exitApp();
    }



    private void run(){
        System.out.print("Please enter the width of the table: ");
        int width = input.nextInt();
        System.out.print("Please enter the height of the table:: ");
        int height = input.nextInt();
        this.height = height;
        this.width = width;
        build(height, width);
        difMenu();

    }
    private void build(int height, int width) {
        table = new String[height][width];
    }
    private void random() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = ".";
            }
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
        while (bombs > 0) {
            int h = (int) (Math.random() * (height - 0)) + 0;
            int w = (int) (Math.random() * (width - 0)) + 0;
            table[h][w] = "*";
            bombs--;
        }

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
