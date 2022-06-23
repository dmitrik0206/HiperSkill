import java.util.Scanner;

class Main {
    public static String[][] starCreator(int size) {
        String[][] star = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boolean notDot = i == j || j == size - i - 1 || j == size / 2 || i == size / 2;
                star[i][j] = notDot ? "*" : ".";
            }
        }
        return star;
    }

    public static void printStar(String[][] star) {
        for (String[] line : star) {
            System.out.println(String.join(" ", line));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printStar(starCreator(scanner.nextInt()));
    }
}