package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.Tiles.Tile;


public class OwnableT extends Tile {

    protected Player owner;
    protected int price, rent, knowledge;

    public OwnableT(String name, int rent, int knowledge, int price) {
        super(name);
        this.price = price;
        this.rent = rent;
        this.knowledge = knowledge;
    }

    public void doAction(Player p, Board b) {

        if (owner != null) {
            p.pay(rent);
            owner.getPayed(rent);
        }

    }

    public void buyTile(Player p) {
        p.buyTile(this);
    }

    public int getPrice() {
        return this.price;
    }

}
