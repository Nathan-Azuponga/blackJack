package blackJack;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJackGame {
    private final Map<Player, Integer> scores;
    private final Map<Player, List<Card>> playerList;
    private final Deck deck;
    private final List<Player> eliminatedPlayers;
    private final Map<Player, Integer> options;
    private final List<Player> players;
    Scanner scanner;
    private boolean status = false;

    public BlackJackGame(Deck deck) {
        this.deck = deck;
        playerList = new HashMap<>();
        options = new HashMap<>();
        scores = new HashMap<>();
        eliminatedPlayers = new ArrayList<>();
        players = new ArrayList<>();
    }


    public void distributeCards() {
        for (Player player : players) {
            deal(player, 2);
        }
    }


    public void createPlayers(int numberOfPlayers) {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player("Player " + (i + 1), new DefaultStrategy()));
        }
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
        return playerList.get(player)
                .stream()
                .map(x -> x.getRank().getValue())
                .reduce(0, Integer::sum);
    }

    public void hit(Player player) {
        deal(player, 1);
    }

    public void displayPlayerCards(Player player) {
        System.out.println(player.getName() + " cards => " + playerList.get(player)
                .stream()
                .map(this::format)
                .collect(Collectors.toList()));
    }

    private String format(Card card) {
        return card.getRank() + " of " + card.getSuit();
    }

    public void playGame() {
        distributeCards();
        System.out.println("1. Hit\n2. Stick");

        scanner = new Scanner(System.in);

        while (!status) {
            for (Player player : players) {
                displayPlayerCards(player);
                System.out.print(player.getName() + "'s turn: ");

                if (player.getPlayerStatus() == PlayerStatus.IN_GAME) {
                    Action action = player.getStrategy().getAction(getTotal(player));
                    if (action == Action.HIT) {
                        deal(player, 1);

                        int handValue = getTotal(player);
                        if (handValue > 21) {
                            player.setPlayerStatus(PlayerStatus.BUSTED);
                            ejectPlayers();

                        } else if (handValue == 21) {
                            System.out.printf("%s wins", player.getName());
                            status = false;
                        }

                    } else if (action == Action.STICK) {
                        player.setPlayerStatus(PlayerStatus.STUCK);
                    }

                }

//                int option = scanner.nextInt();
//
//                options.put(player, option);
//
//                if (option == 1) {
//                    deal(player, 1);
//                }
//
//                displayPlayerCards(player);
//                System.out.println();
//
//                scores.put(player, getValueOfCards(player));
//
//                if (i == playerList.size() - 1) {
//                    decide();
//                    scores.clear();
//                }
            }
            ejectPlayers();
        }
    }

    public int getValueOfCards(Player player) {
        return playerList.get(player)
                .stream()
                .map(x -> x.getRank().getValue())
                .reduce(0, Integer::sum);
    }

    private void decide() {

        int highest = 0;

        long stickCount = options.values().stream().filter(x -> x == 2).count();

        if (stickCount == players.size()) {
            Player player = null;

            for (Map.Entry<Player, Integer> item : scores.entrySet()) {
                int score = getValueOfCards(item.getKey());

                if (score > highest && score < 22) {
                    highest = score;
                    player = item.getKey();
                }
            }

            if (player != null) {
                System.out.println(player.getName() + " won");
            }
            status = true;
            return;
        }

        // check if there is a winner

        Optional<Player> winner = playerList
                .keySet()
                .stream()
                .filter(player -> getValueOfCards(player) == 21)
                .findFirst();

        if (winner.isPresent()) {
            System.out.println(winner.get().getName() + " won");
            status = true;
            return;
        }

        //eliminate players that do not qualify for the next round
        for (Map.Entry<Player, Integer> item : scores.entrySet()) {
            int score = getValueOfCards(item.getKey());

            if (score > 21) {
                eliminatedPlayers.add(item.getKey());
                System.out.println(item.getKey().getName() + " is out of the game\n");
            }
        }
    }

    private void ejectPlayers() {
        eliminatedPlayers.forEach(player -> {
            scores.remove(player);
            playerList.remove(player);
        });

        players.removeAll(eliminatedPlayers);

        //end game if number of remaining players is one
        if (players.size() == 1) {
            System.out.println(players.get(0).getName() + " won");
            status = true;
        }

        if (players.size() == 0) {
            System.out.println("No winner thank you");
            status = true;
        }
    }

    @Override
    public String toString() {
        return "BlackJackGame{" +
                "playerList=" + playerList +
                ", deck=" + deck +
                '}';
    }
}
