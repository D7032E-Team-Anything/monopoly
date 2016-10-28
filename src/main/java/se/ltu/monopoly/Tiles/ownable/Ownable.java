package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.Tiles.Tile;


public class Ownable implements Tile {

    protected Player owner;
    protected int price, rent;

    public Ownable(int rent, int price) {
        this.price = price;
        this.rent = rent;
    }

    public void doAction(Player p) {

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

    public boolean hasOwner() {

        return owner != null;

    }

}
