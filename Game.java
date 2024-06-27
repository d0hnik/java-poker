import Cards.Deck;

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to start? (yes/no)");
        String userResponse = scanner.nextLine().trim().toLowerCase();

        if (userResponse.equals("yes") || userResponse.equals("y")) {
            Deck deck = new Deck();
            deck.initializeDeck();
            System.out.println("Mäng algab! Teeme ettevalmistusi...");

        } else if (userResponse.equals("no") || userResponse.equals("n")) {
            System.out.println("Mäng lõpetatakse. Head päeva!");
            System.exit(0);
        } else {
            System.out.println("Vale sisestus. Palun vastake 'jah' või 'ei'.");
        }

        scanner.close();
    }
}
