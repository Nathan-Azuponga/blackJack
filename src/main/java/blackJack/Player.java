package blackJack;

public class Player {
    private static int playerCount = 1;

    private String name;
    private final Strategy strategy;

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    private PlayerStatus playerStatus;

    public Player(String name, Strategy strategy) {
        this.name = name;
        playerStatus = PlayerStatus.IN_GAME;
        this.strategy = strategy;
        playerCount++;
//        hand = new ArrayList<>();
    }


    public static int getPlayerCount() {
        return playerCount;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "name";
    }

    public Strategy getStrategy() {
        return strategy;
    }
}
