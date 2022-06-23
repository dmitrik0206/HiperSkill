package machine;

import java.util.Scanner;

public class CoffeeMachine {
    Scanner scanner = new Scanner(System.in);
    int[] costsOfCoffeeMachine = { 400, 540, 120, 9, 550 };
    int[][] cupsOfCoffee = {
            { 250, 0, 16, 1, -4 },
            { 350, 75, 20, 1, -7 },
            { 200, 100, 12, 1, -6 } };


    void printRemaining() {
        System.out.printf("The coffee machine has:\n%d ml of water\n" +
                "%d ml of milk\n%d g of coffee beans\n" +
                "%d disposable cups\n$%d of money%n",
                costsOfCoffeeMachine[0], costsOfCoffeeMachine[1],
                costsOfCoffeeMachine[2], costsOfCoffeeMachine[3],
                costsOfCoffeeMachine[4]);
    }

    boolean checkRemains(int number) {
        String[] ingredients = {"water", "milk", "coffee beans"};
        for (int i = 0; i < ingredients.length; i++) {
            if (costsOfCoffeeMachine[i] - cupsOfCoffee[number][i] < 0) {
                System.out.printf("Sorry, not enough %s!%n", ingredients[i]);
                return false;
            }
        }
        return true;
    }

    void brewCoffee(int number) {
        if (checkRemains(number)) {
            System.out.println("I have enough resources, making you a coffee!");
            for (int i = 0; i < costsOfCoffeeMachine.length; i++) {
                costsOfCoffeeMachine[i] -= cupsOfCoffee[number][i];
            }
        }
    }

    void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        switch (scanner.nextLine()) {
            case "back":
                return;
            case "1":
                brewCoffee(0);
                break;
            case "2":
                brewCoffee(1);
                break;
            case "3":
                brewCoffee(2);
                break;
        }
    }

    void fill() {
        System.out.println("Write how many ml of water you want to add:");
        costsOfCoffeeMachine[0] += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        costsOfCoffeeMachine[1] += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        costsOfCoffeeMachine[2] += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        costsOfCoffeeMachine[3] += scanner.nextInt();
    }

    void take() {
        System.out.printf("I gave you $%d%n", costsOfCoffeeMachine[4]);
        costsOfCoffeeMachine[4] = 0;
    }

    void menu() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            switch (scanner.nextLine()) {
                case "exit":
                    return;
                case "buy":
                    buy();
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printRemaining();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.menu();
    }
}
