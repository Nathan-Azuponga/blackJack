package blackJack;

public interface Strategy {
    public Action getAction(int handValue);
}
