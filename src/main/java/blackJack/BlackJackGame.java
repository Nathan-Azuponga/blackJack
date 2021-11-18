package blackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    Map<Player, List<Card>> playerList;
    Deck deck;

    public BlackJackGame(Deck deck) {
        this.deck = deck;
    }


    private void deal(Player player, int numberOfCards) {
        List<Card> cards = playerList.get(player) == null ? new ArrayList<>() : playerList.get(player);

        for (int i = 1; i <= numberOfCards; i++) {
            cards.add(deck.getCardStack().pop());
        }

        playerList.put(player, cards);
    }

    @Override
    public String toString() {
        return "BlackJackGame{" +
                "playerList=" + playerList +
                ", deck=" + deck +
                '}';
    }
}
