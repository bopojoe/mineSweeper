
/**
 * @file Problem110102
 * @author James O'Rourke_20074556
 * @assignment Problem110102 Assignment
 * @brief
 * This application takes inputs and converts them into a Minesweeper Field
 *
 * @notes
 * The 300 odd lines that follow run through the process of converting inputs to characters,
 * putting them into a 2D array and then checking which locations hold the "*"
 * There is an internal class which i use to make objects and store in the Arraylist
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Problem110102 {
    Scanner input;
    char[][] test;
    private int height = 1;
    private int width;
    private Table staticTable;
    private ArrayList<Table> list = new ArrayList<>();

    private int count = 0;

    //builds scanner and runs program
    public Problem110102() {
        input = new Scanner(System.in);
        run();
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        Problem110102 app = new Problem110102();
    }

    //calls input and adds input to arrayList
//basically the guts of the program
    private void run() {
        while (height != 0) {
            input();
            list.add(count, staticTable);
            count++;

        }

        zeroArray();
        changeMarkers();
        printOut();
        exitApp();
    }

    //takes input numbers and makes a 2D array to store the field
    private void input() {
        String size = input.nextLine();
        String[] dimensions = size.split(" ");
        height = Integer.parseInt(dimensions[0]);
        width = Integer.parseInt(dimensions[1]);
        if ((height != 0 || width != 0)&&(height <= 100 && width <= 100)) {
            Table tableOne = new Table();
            tableOne.table = new char[height][width];
            staticTable = tableOne;
            readMatrix(height, width);
        }
    }

    //method for reading in the field to build the 2D array
    private void readMatrix(int height, int width) {
        String[] lines;
        lines = new String[height];
        for (int i = 0; i < height; i++) {

            lines[i] = input.nextLine();
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String check = lines[i];
                staticTable.table[i][j] = check.charAt(j);
            }

        }
    }

    //method for adding the numbers to the field
//checks the position of bombs found and depending on position adds 1 to all positions available
//does this for each field in the arrayList
    private void changeMarkers() {
        int k = 0;
        while (count - 1 > k) {
            Table table = list.get(k);
            test = table.getTable();
            int h = test.length - 1;

            int w = test[0].length - 1;

            for (int i = 0; i < test.length; i++) {

                for (int j = 0; j < test[i].length; j++) {
                    char c = '*';
                    int x = 0;
                    int y = 0;
                    if (c == test[i][j]) {
                        x = i;
                        y = j;
                        if ((x < h && x > 0)) {
                            if (y < w && y > 0) {
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j - 1);
                                helperH1(test, i - 1, j + 1);

                                helperH1(test, i, j - 1);
                                helperH1(test, i, j + 1);

                                helperH1(test, i + 1, j);
                                helperH1(test, i + 1, j - 1);
                                test = helperH1(test, i + 1, j + 1);
                            }
                            if (y == 0) {
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j + 1);
                                helperH1(test, i, j + 1);
                                helperH1(test, i + 1, j);
                                test = helperH1(test, i + 1, j + 1);


                            }
                            if (y == w) {
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j - 1);
                                helperH1(test, i, j - 1);
                                helperH1(test, i + 1, j);
                                test = helperH1(test, i + 1, j - 1);

                            }


                        } else if (x == 0) {

                            if (y < w && y > 0) {
                                helperH1(test, i, j - 1);
                                helperH1(test, i, j + 1);
                                helperH1(test, i + 1, j);
                                helperH1(test, i + 1, j - 1);
                                test = helperH1(test, i + 1, j + 1);


                            }
                            if (y == w) {
                                helperH1(test, i + 1, j);
                                helperH1(test, i + 1, j - 1);
                                test = helperH1(test, i, j - 1);

                            }
                            if (y == 0) {
                                helperH1(test, i + 1, j);
                                helperH1(test, i + 1, j + 1);
                                test = helperH1(test, i, j + 1);

                            }


                        } else if (x == h) {
                            if (y < w && y > 0) {
                                helperH1(test, i, j - 1);
                                helperH1(test, i, j + 1);
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j - 1);
                                test = helperH1(test, i - 1, j + 1);

                            }

                            if (y == w) {
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j - 1);
                                test = helperH1(test, i, j - 1);

                            }
                            if (y == 0) {
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j + 1);
                                test = helperH1(test, i, j + 1);

                            }
                        }

                    }


                }
            }
            k++;
        }
    }

    //this is a helper class for the last class as its called multiple times
    private char[][] helperH1(char[][] array, int x1, int y1) {
        if (!checkBomb(array, x1, y1)) {
            String str = "" + array[x1][y1];
            if (parseCheck(str)) {
                int value = Integer.parseInt(str);
                value = value + 49;
                array[x1][y1] = (char) value;
                return array;
            } else {
                array[x1][y1] = (char) 49;
            }
        }
        return array;
    }

    //this is the method to check the position for a bomb
    private boolean checkBomb(char[][] array, int xPos, int yPos) {
        char c = '*';

        if (c == array[xPos][yPos]) {
            return true;
        } else {
            return false;
        }


    }

    //this method was added after a bug was found
//so now at the start after the 2D array is made
//the array is zeroed out apart from the bombs
    private void zeroArray() {
        int k = 0;
        while (count - 1 > k) {
            Table table = list.get(k);
            test = table.getTable();
            for (int i = 0; i < test.length; i++) {
                for (int j = 0; j < test[i].length; j++) {
                    test = checkBombZero(test, i, j);
                }

            }
            k++;
        }

    }

    //this method is a helper for the last it checks position for bombs and changes '.'to '0'(48 in unicode)
    private char[][] checkBombZero(char[][] array, int xPos, int yPos) {
        char c = '*';

        if (c == array[xPos][yPos]) {
            return array;
        } else {
            array[xPos][yPos] = (char) 48;
            return array;
        }


    }

    //this method is used to check if what is being taken form the array is parseable
    private boolean parseCheck(String test) {

        boolean parseable = true;
        try {
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            parseable = false;
        }
        return parseable;
    }

    //method to cycle through the 2D array and print what is found
    private void printOut() {
        int k = 0;
        while (count - 1 > k) {
            Table table = list.get(k);
            test = table.getTable();
            System.out.println("Field #" + (k + 1));
            for (int i = 0; i < test.length; i++) {
                for (int j = 0; j < test[i].length; j++) {
                    System.out.print(test[i][j] + " ");
                }
                System.out.println("");
            }
            System.out.println("");
            k++;
        }

    }

    //method to nicely exit the app
    private void exitApp() {

        System.out.println("Exiting App");
        System.exit(0);
    }

    //class to make an object of to store multiple 2D arrays in an ArrayList
    private class Table {
        protected char[][] table;

        public Table() {


        }

        public char[][] getTable() {
            return table;
        }


    }


}

