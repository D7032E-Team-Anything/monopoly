package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Philm extends Ownable {

    public Philm(int position, String name, int rent, int price) {
        super(position, name, rent, price);
    }

    @Override
    public void onAction(Player p, Board b) {
        super.onAction(p, b);
    }

    @Override
    public String toString() {
        return "Philm";
    }

}
