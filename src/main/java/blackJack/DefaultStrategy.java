package blackJack;

public class DefaultStrategy implements Strategy {
    @Override
    public Action getAction(int handValue) {
        if (handValue < 17) {
            return Action.HIT;
        }
        return Action.STICK;
    }
}