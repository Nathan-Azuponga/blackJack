package blackJack;


import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var players = List.of(
                new Player("Player 1"),
                new Player("Player 2"),
                new Player("Player 3")
        );

        var deck = new Deck();
        System.out.println(deck.getCardStack());

        var blackJack = new BlackJackGame(deck);
        for (Player player : players) {
            blackJack.deal(player, 2);
        }

        boolean status = true;
        System.out.println("1. Hit\n2. Stick");

        Scanner scanner = new Scanner(System.in);

        while (status) {
            for (Map.Entry<Player, List<Card>> items : blackJack.playerList.entrySet()) {
                int total = blackJack.getTotal(items.getKey());
                if (total == 21) {
                    System.out.println(items.getKey().getName() + " Won ");
                    status = false;
                } else if (total > 21) {
                    System.out.println(items.getKey().getName()+ " busted");
                    status = false;
                }else{
                    System.out.println(items.getKey().getName() + "'s turn\n hit or stick");
                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        blackJack.hit(items.getKey());
                    }
                    blackJack.displayPlayerCards(items.getKey());
                }

            }
        }

//
//        try {
//            var result = args.length == 1 ? Integer.parseInt(args[0]) : 3;
//            if (result < 0 || result > 6)
//                throw new IllegalArgumentException();
//        } catch (Exception e) {
//
//            System.out.println("Exception throw");
//        }

    }
}
