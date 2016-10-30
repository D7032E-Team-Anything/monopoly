package se.ltu.monopoly;

public interface Action {
    /**
     * The Action interface is implemented by all the entities that modify the state of
     * the game.
     * */
    void onAction(Player p, Board b);

    String getMessage();
}