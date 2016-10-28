package se.ltu.monopoly.chanceCards;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;

/**
 * Created by haidar on 2016-10-27.
 */
public interface ChanceCard {

    void onAction(Player p, Board b);

}
