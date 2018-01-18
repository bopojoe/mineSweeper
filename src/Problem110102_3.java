/**
 * @author James O'Rourke_20074556 on 17/01/2018
 */


import java.util.ArrayList;
import java.util.Scanner;
public class Problem110102_3 {
    Scanner input;
    private int height=1;
    private int width;
    char[][] test;
    private Table staticTable;
    private ArrayList<Table> list = new ArrayList<>();

    private int count = 0;

    public Problem110102_3(){
        input = new Scanner(System.in);
        run();
    }

    private void run(){
        while(height!=0){
            input();
            list.add(count,staticTable);
            count++;

        }
        printOut();
        changeMarkers();
        printOut();
        exitApp();
    }
    private void exitApp() {

        System.out.println("Exiting App");
        System.exit(0);
    }

    private void input(){
        String size = input.nextLine();
        height = Integer.parseInt(""+size.charAt(0));
        width = Integer.parseInt(""+size.charAt(2));
        if(height!=0) {
            Table tableOne = new Table();

            tableOne.table = new char[height][width];
            staticTable = tableOne;
            readMatrix(height, width);
        }
    }

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

    private void changeMarkers() {
        int k = 0;
        while (count-1 > k) {
            Table table = list.get(k);
            test = table.getTable();
            int h = test.length;

            int w = test[0].length;

            for (int i = 0; i < test.length; i++) {

                for (int j = 0; j < test[i].length; j++) {
                    char c = '*';
                    int zero = 0;
                    int x=0;
                    int y=0;
                    if(c == test[i][j]){
                        x=i;
                        y=j;
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
                            if (y < w && y == 0) {
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j+1);
                                helperH1(test, i , j+1);
                                helperH1(test, i +1, j);
                                helperH1(test, i + 1, j+1);


                            }
                            if (y == w && y > 0) {
                                helperH1(test, i - 1, j);
                                helperH1(test, i - 1, j-1);
                                helperH1(test, i , j-1);
                                helperH1(test, i + 1, j);
                                helperH1(test, i +1, j-1);

                            }


                            }else if(x<h&&x==0) {

                            if ((x < h && x > 0)) {
                                if (y < w && y > 0) {

                                }
                                if (y < w && y > 0) {

                                }
                                if (y == w && y > 0) {

                                }
                                if (y == w && y == 0) {

                                }


                        }else if(x==h&&x>0) {
                                if (y < w && y > 0) {

                                }
                                if (y < w && y > 0) {

                                }
                                if (y == w && y > 0) {

                                }
                                if (y == w && y == 0) {

                                }
                            }

                        }else if(x==h&&x==0) {
                            if (y < w && y > 0) {

                            }
                            if (y < w && y > 0) {

                            }
                            if (y == w && y > 0) {

                            }
                            if (y == w && y == 0) {

                            }

                        }


                    }
                }
            }k++;
        }

        //System.out.println(k);
    }
private void helper2(char[][] array, int i, int j){
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
    if (y < w && y == 0) {
        helperH1(test, i - 1, j);
        helperH1(test, i - 1, j+1);
        helperH1(test, i , j+1);
        helperH1(test, i +1, j);
        helperH1(test, i + 1, j+1);


    }
    if (y == w && y > 0) {
        helperH1(test, i - 1, j);
        helperH1(test, i - 1, j-1);
        helperH1(test, i , j-1);
        helperH1(test, i + 1, j);
        helperH1(test, i +1, j-1);

    }
}

    private char[][] helperH1(char[][] array, int x1, int y1){
        if (!checkBomb(array, x1, y1)) {
            String str = "" + array[x1][y1];
            if (parseCheck(str)) {
                int value = Integer.parseInt(str);
                value = value+49;
                array[x1][y1] = (char) value;
                return array;
            } else {
                array[x1][y1] = (char) 49;
            }
        }
        return array;
    }
    private boolean checkBomb(char[][] array, int xPos, int yPos){
        char c = '*';

        if(c == array[xPos][yPos]){
            return true;
        }else{return false;}


    }


    private boolean parseCheck(String test) {

        boolean parseable = true;
        try {
            Integer.parseInt(test);
        } catch (NumberFormatException e) {
            parseable = false;
        }
        return parseable;
    }







    private void printOut() {
        int k = 0;
        while (count-1 > k) {
            Table table = list.get(k);
            test = table.getTable();
            System.out.println("Field #"+(k+1));
            for (int i = 0; i < test.length; i++) {
                for (int j = 0; j < test[i].length; j++) {
                    System.out.print(test[i][j] + " ");
                }
                System.out.println("");
            }
            k++;
        }

    }


    private class Table {
        protected char[][] table;

        public char[][] getTable() {
            return table;
        }

        public Table(){


        }


    }


}

