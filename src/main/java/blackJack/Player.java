package blackJack;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public void addCards(Card card) {
        hand.add(card);
    }

    public String toString() {
        return "name";
    }

    public String getName() {
        return name;
    }
}
