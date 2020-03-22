package tictactoe;
import java.util.Scanner;

public class Main {
    public static void showStartField(String str) {
        int size = 3;
        System.out.println("---------");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                int indx = i * size + j;
                if (str.charAt(indx) == '_') {
                    System.out.print("  ");
                } else {
                    System.out.print(str.charAt(indx) + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static int[][] createMtx(int size) {
        int[][] tic = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tic[i][j] = 0;
            }
        }
        return tic;
    }
    public static int checkField(int[][] tic, int size) {
        boolean xx = false;
        boolean oo = false;
        int check = 0;
        if (Math.abs(check = tic[0][0] + tic[1][1] + tic[2][2]) == 3 ||
                (Math.abs(check = tic[0][2] + tic[1][1] + tic[2][0]) == 3)) {
            if (check == 3) {
                xx = true;
            } else if (check == -3) {
                oo = true;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (!xx || !oo) {
                    int outIndx = (((i - 1) % size) + size) % size;
                    if (Math.abs(check = tic[i][outIndx] + tic[i][i] + tic[i][(i + 1) % size]) == 3 ||
                            (Math.abs(check = tic[outIndx][i] + tic[i][i] + tic[(i + 1) % size][i]) == 3)) {
                        if (check == 3) {
                            xx = true;
                        } else if (check == -3) {
                            oo = true;
                        }
                    }
                }
            }
        }
        int sum = 0;
        int zeroCnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tic[i][j] == 0) {
                    zeroCnt++;
                } else {
                    sum += tic[i][j];
                }
            }
        }
        if (xx && oo || Math.abs(sum) >= 2) {
            //System.out.println("Impossible");
            return -1;
        } else if (xx) {
            System.out.println("X wins");
        } else if (oo) {
            System.out.println("O wins");
        } else if (zeroCnt == 0) {
            System.out.println("Draw");
        } else {
            //System.out.println("Game not finished");
            return 0;
        }
        return 1;
    }

    public static void showEnterMsg() {
        System.out.print("Enter the coordinates: ");
    }

    public static int checkCoords(String coords, int[][] tic, int size) {
        if (coords.charAt(0) >= '0' && coords.charAt(1) == ' ' && coords.charAt(2) <= '9') {
            int x = coords.charAt(0) - 48;
            int y = coords.charAt(2) - 48;
            if (x >= 1 && x <= 3 && y >= 1 && y <= 3) {
                if (tic[size - y][x - 1] == 0) {
                    return 1;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    return -3;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                return -2;
            }
        } else {
            System.out.println("You should enter numbers!");
            return -1;
        }
    }

    public static String getCoords() {
        String coords = new Scanner(System.in).nextLine();
        return coords;
    }

    public static int[][] fillField(int[][] tic, int x, int y, int size, int val) {
        tic[size - y][x - 1] = val;
        return tic;
    }

    public static void showField(int[][] tic, int size) {
        System.out.println("---------");
        for (int i = 0; i < size; i++) {
            System.out.print("| ");
            for (int j = 0; j < size; j++) {
                if (tic[i][j] == 1) {
                    System.out.print("X ");
                } else if (tic[i][j] == -1) {
                    System.out.print("O ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    public static void main(String[] args) {
        int size = 3;
        int[][] tic = createMtx(size);
        showField(tic, size);
        int round = 1;
        while (checkField(tic, size) == 0) {
            showEnterMsg();
            String coords = getCoords();
            while (checkCoords(coords, tic, size) < 0) {
                showEnterMsg();
                coords = getCoords();
            }
            int x = coords.charAt(0) - 48;
            int y = coords.charAt(2) - 48;
            tic = fillField(tic, x, y, size, round);
            round *= -1;
            showField(tic, size);
        }
    }
}
