package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;
import se.ltu.monopoly.Tiles.Tile;


public class Ownable implements Action, Tile {

    private String      message;
    protected Player    owner;
    protected int       price;
    protected int       rent;
    protected String    name;
    protected int       position;

    /**
     * The Onable class represent a tile that can be owned by a player.
     * @param position ownable tile position
     * @param name     ownable tile name
     * @param rent     the cost of renting this tile
     * @param price    the cost to purchase this tile
     * */

    public Ownable(int position, String name,int rent, int price) {
        this.position = position;
        this.name = name;
        this.price = price;
        this.rent = rent;
    }

    public void onAction(Player p, Board b) {

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

    public int getRent() {
        return rent;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }
}
