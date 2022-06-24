package tictactoe;

import java.util.Scanner;

class TicTacToe {
    Scanner scanner = new Scanner(System.in);
    private String gameLine = "_________";
    private final int size = 3;
    char[] players = {'X', 'O'};

    public String getGameLine() {
        return gameLine;
    }

    public void printField() {
        String dashes = "---------";
        System.out.println(dashes);
        String changedGameLine = getGameLine().replace('_', ' ');
        for (int i = 0; i < size; i++) {
            String[] subArrayOfThird = changedGameLine.substring(i * size, size * (i + 1)).split("");
            System.out.printf("| %s |%n", String.join(" ", subArrayOfThird));
        }
        System.out.println(dashes);
    }

    public boolean checkHorizontal(int winInt, int i) {
        int sum = 0;
        for (char ch : getGameLine().substring(i * size, size * (i + 1)).toCharArray()) {
            sum += ch;
        }
        return sum == winInt;
    }

    public boolean checkMainDiagonal(int winInt) {
        int sum = 0;
        for (int j = 0; j < size; j++) {
            sum += getGameLine().charAt(size * j + j);
        }
        return sum == winInt;
    }

    public boolean checkSecondaryDiagonal(int winInt) {
        int sum = 0;
        for (int j = 0; j < size; j++) {
            sum += getGameLine().charAt(size * j + size - 1 - j);
        }
        return sum == winInt;
    }

    public boolean checkVertical(int winInt, int i) {
        int sum = 0;
        for (int j = 0; j < size; j++) {
            sum += getGameLine().charAt(i + j * size);
        }
        return sum == winInt;
    }

    public boolean checkWin(int winInt) {
        for (int i = 0; i < size; i++) {
            if (checkHorizontal(winInt, i) || checkVertical(winInt, i)) {
                return true;
            }
        }
        return checkMainDiagonal(winInt) || checkSecondaryDiagonal(winInt);
    }

    public boolean isO_Win() {
        int o = 'O' * size;
        return checkWin(o);
    }

    public boolean isX_Win() {
        int x = 'X' * size;
        return checkWin(x);
    }

    public int charCounter(char ch) {
        int counter = 0;
        for (char charOfLine : gameLine.toCharArray()) {
            counter += charOfLine == ch ? 1 : 0;
        }
        return counter;
    }

    public boolean isNotWin() {
        printField();
        int count_ = charCounter('_');
        if (count_ == 0 && !isX_Win() && !isO_Win()) {
            System.out.println("Draw");
            return false;
        } else if (isO_Win() || isX_Win()) {
            System.out.printf("%s wins%n", isX_Win() ? "X" : "O");
            return false;
        }
        return true;
    }

    private int converterStringToIndexGameLine(String coordinates) {
        String[] twoStrings = coordinates.split(" ");
        int row = Integer.parseInt(twoStrings[0]) - 1;
        int col = Integer.parseInt(twoStrings[1]) - 1;
        return row * size + col;
    }

    private boolean isNotFreeCoordinates(String coordinates) {
        return getGameLine().charAt(converterStringToIndexGameLine(coordinates)) != '_';
    }

    private void changeGameLine(String coordinates, char ch) {
        char[] arrayOfGameLine = gameLine.toCharArray();
        arrayOfGameLine[converterStringToIndexGameLine(coordinates)] = ch;
        gameLine = new String(arrayOfGameLine);
    }

    public void checkCoordinates(char ch) {
        while (true) {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            if (!coordinates.matches("\\d \\d")) {
                System.out.println("You should enter numbers!");
            } else if (!coordinates.matches("[1-3] [1-3]")) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (isNotFreeCoordinates(coordinates)) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                changeGameLine(coordinates, ch);
                break;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        int counter = 0;
        while (game.isNotWin()) {
            char player = game.players[counter];
            game.checkCoordinates(player);
            counter = counter == 0 ? 1 : 0;
        }
    }
}