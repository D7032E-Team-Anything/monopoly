package se.ltu.monopoly;

public interface Action {

    void onAction(Player p, Board b);

    String message();
}
