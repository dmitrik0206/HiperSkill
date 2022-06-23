package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    Scanner scanner = new Scanner(System.in);
    private final int expensiveTicket = 10;
    private final int cheapTicket = 8;
    private int widthOfRoom;
    private int lengthOfRoom;
    private String[][] seats;
    private final int[] seat = new int[2];
    private int numberOfTickets = 0;
    private int totalIncome = 0;
    private int currentIncome = 0;
    private double percentage = 0;

    public int getWidthOfRoom() {
        return widthOfRoom;
    }

    public void setWidthOfRoom(int widthOfRoom) {
        this.widthOfRoom = widthOfRoom;
    }

    public int getLengthOfRoom() {
        return lengthOfRoom;
    }

    public void setLengthOfRoom(int lengthOfRoom) {
        this.lengthOfRoom = lengthOfRoom;
    }

    private boolean isSmallRoom() {
        int limitOfSmallRoom = 60;
        return getLengthOfRoom() * getWidthOfRoom() <= limitOfSmallRoom;
    }

    private int calculateProfitBigCinemaRoom() {
        int firstHalf = getLengthOfRoom() / 2;
        int secondHalf = getLengthOfRoom() - firstHalf;
        int costFirstHalfTickets = firstHalf * getWidthOfRoom() * expensiveTicket;
        int costSecondHalfTickets = secondHalf * getWidthOfRoom() * cheapTicket;
        return costFirstHalfTickets + costSecondHalfTickets;
    }

    private void calculateProfitOfCinemaRoom() {
        if (isSmallRoom()) {
            totalIncome = getLengthOfRoom() * getWidthOfRoom() * expensiveTicket;
            return;
        }
        totalIncome = calculateProfitBigCinemaRoom();
    }

    private int calculateCostOfTicket() {
        if (isSmallRoom()) {
            return expensiveTicket;
        }
        return seat[0] <= getLengthOfRoom() / 2 ? expensiveTicket : cheapTicket;
    }

    private void calculatePercentage() {
        final double ten = 100;
        percentage = numberOfTickets * ten / (getLengthOfRoom() * getWidthOfRoom());
    }

    private void priceTicket() {
        int priceOfTicket = calculateCostOfTicket();
        currentIncome += priceOfTicket;

        System.out.printf("Ticket price: $%d%n", priceOfTicket);
    }

    private void enterLengthRoom() {
        System.out.println("Enter the number of rows:");
        setLengthOfRoom(Integer.parseInt(scanner.nextLine()));
    }

    private void enterWidthRoom() {
        System.out.println("Enter the number of seats in each row:");
        setWidthOfRoom(Integer.parseInt(scanner.nextLine()));
    }

    private void setSizeRoom() {
        seats = new String[getLengthOfRoom()][getWidthOfRoom()];
        for (int i = 0; i < lengthOfRoom; i++) {
            Arrays.fill(seats[i], "S");
        }
    }

    private void printSeats() {
        System.out.print("Cinema:\n ");
        for (int i = 1; i <= widthOfRoom; i++) {
            System.out.printf(" %d", i);
        }
        System.out.println();
        for (int i = 0; i < lengthOfRoom; i++) {
            System.out.printf("%d ", i + 1);
            System.out.println(String.join(" ", seats[i]));
        }
    }

    private void enterRowSeat() {
        System.out.println("Enter a row number:");
        seat[0] = Integer.parseInt(scanner.nextLine());
    }

    private void enterSeatNumber() {
        System.out.println("Enter a seat number in that row:");
        seat[1] = Integer.parseInt(scanner.nextLine());
    }

    private void setSeatInSeats() {
        int rowSeat = seat[0] - 1;
        int numberOfSeat = seat[1] - 1;
        seats[rowSeat][numberOfSeat] = "B";
    }

    private void printMenu() {
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
    }

    private boolean checkNumberSeat() {
        if (checkRange(getLengthOfRoom(), seat[0]) || checkRange(getWidthOfRoom(), seat[1])) {
            System.out.println("Wrong input!");
            return true;
        }
        return false;
    }

    private static boolean checkRange(int limit, int number) {
        return number <= 0 || number > limit;
    }

    private boolean checkFreeSeat() {
        int rowSeat = seat[0] - 1;
        int numberOfSeat = seat[1] - 1;
        if ("B".equals(seats[rowSeat][numberOfSeat])) {
            System.out.println("That ticket has already been purchased!");
            return true;
        }
        return false;
    }

    private void buyTicket() {
        do {
            enterRowSeat();
            enterSeatNumber();
        } while (checkNumberSeat() || checkFreeSeat());

        priceTicket();
        setSeatInSeats();
        numberOfTickets++;
        calculatePercentage();
    }

    private void enterSizeRoom() {
        enterLengthRoom();
        enterWidthRoom();
        setSizeRoom();
        calculateProfitOfCinemaRoom();
    }

    private void getStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", numberOfTickets);
        System.out.printf("Percentage: %.2f%%%n", percentage);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    public void menu() {
        enterSizeRoom();
        while (true) {
            printMenu();
            switch (scanner.nextLine()) {
                case "0":
                    return;
                case "1":
                    printSeats();
                    break;
                case "2":
                    buyTicket();
                    break;
                case "3":
                    getStatistics();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.menu();
    }
}