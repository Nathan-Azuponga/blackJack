package blackJack;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        var playerList = List.of(
                new Player("Player 1"),
                new Player("Player 1"),
                new Player("Player 1")
        );

        var deck = new Deck();
        System.out.println(deck.getCardStack());

        var blackJack = new BlackJackGame(deck);

        try {
            var result = args.length == 1 ? Integer.parseInt(args[0]) : 3;
            if (result < 0 || result > 6)
                throw new IllegalArgumentException();
        } catch (Exception e) {

            System.out.println("Exception throw");
        }

    }
}
