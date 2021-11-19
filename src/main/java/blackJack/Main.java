package blackJack;

public class Main {
    public static void main(String[] args) {

        try {
            int result = args.length == 1 ? Integer.parseInt(args[0]) : 3;
            if (result <= 1 || result > 6)
                return;
//                throw new IllegalArgumentException();


            var deck = new Deck();
            System.out.println(deck.getCardStack().size());
            var blackJack = new BlackJackGame(deck);
            deck.shuffle();

            blackJack.createPlayers(result);

            blackJack.playGame();


        } catch (Exception e) {

            System.out.println("Exception thrown");
        }

    }
}
