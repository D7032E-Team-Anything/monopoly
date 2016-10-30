package se.ltu.monopoly;

/**
 * Created by haidar on 2016-10-30.
 */
public interface Action {

    void onAction(Player p, Board b);

    String message();

}
