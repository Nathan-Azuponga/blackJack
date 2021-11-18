package blackJack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackGame {
    Map<Player, List<Card>> playerList;
    Deck deck;

    public BlackJackGame(Deck deck) {
        this.deck = deck;
        playerList = new HashMap<>();
    }

    public Map<Player, List<Card>> getPlayerList() {
        return playerList;
    }

    public void deal(Player player, int numberOfCards) {
        List<Card> cards = playerList.get(player) == null ? new ArrayList<>() : playerList.get(player);

        for (int i = 1; i <= numberOfCards; i++) {
            cards.add(deck.getCardStack().pop());
        }

        playerList.put(player, cards);
    }

    public int getTotal(Player player) {
        return playerList.get(player).stream().map(x -> x.getRank().getValue()).reduce(0, Integer::sum);
    }

    public void hit(Player player) {
        deal(player, 1);
    }

    public void displayPlayerCards(Player player) {
        System.out.println(player.getName() + " cards => "+playerList.get(player).stream().map(x -> format(x)).collect(Collectors.toList()));
    }


    private String format(Card card) {
        return card.getRank() + " of " + card.getSuit();
    }

    @Override
    public String toString() {
        return "BlackJackGame{" +
                "playerList=" + playerList +
                ", deck=" + deck +
                '}';
    }
}
