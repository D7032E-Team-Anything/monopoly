package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.Tiles.Tile;


public class Ownable implements Tile {

    private String message = "";
    protected Player owner;
    protected int price, rent;

    public Ownable(int rent, int price) {
        this.price = price;
        this.rent = rent;
    }

    public void doAction(Player p) {

        if (owner != null) {

            boolean success = p.pay(rent);

            message = p.getName() + " paid the rent to " + owner.getName() +
                    " and has " + p.getMoney() + " study-time left";

            if (success) {
                owner.getPayed(rent);
            } else {
                owner.getPayed(p.getMoney());
                p.pay(p.getMoney());
                message = p.getName() + " Could not afford to pay the rent and has lost";
            }

        }

    }

    public String message() {
        return message;
    }

    public boolean buyTile(Player p) {

        return p.buyTile(this);

    }

    public int getPrice() {
        return this.price;
    }

    public boolean hasOwner() {

        return owner != null;

    }

    public Player getOwner() {
        return owner;
    }

    public int getRent() {
        return rent;
    }



}
