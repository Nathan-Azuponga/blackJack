package blackJack;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!!");

        var deck = new Deck();
        System.out.println(deck.getCardStack().peek());
        deck.shuffle();
        System.out.println(deck.getCardStack().peek());


//        System.out.println(deck.getCardList().get(0));
        //System.out.println(de);
    }
}
