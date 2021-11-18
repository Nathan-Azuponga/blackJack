package blackJack;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cardStack;

    public Deck() {
        cardStack = new Stack<>();

        createCard(Suit.CLUBS);
        createCard(Suit.HEARTS);
        createCard(Suit.DIAMONDS);
        createCard(Suit.SPADES);
    }

    public void createCard(Suit suit) {
        for (Rank rank : Rank.values()) {
            var card = new Card(rank, suit);
            cardStack.add(card);
        }
    }

    public Stack<Card> getCardStack() {
        shuffle();
        return cardStack;
    }

    public void shuffle() {
        Collections.shuffle(cardStack);
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cardStack=" + cardStack +
                '}';
    }
}
