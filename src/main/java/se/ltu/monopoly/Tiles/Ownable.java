package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Action;
import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Ownable implements Action, Tile {

    protected Player owner;
    private String   name;
    private String   message;
    private int      buy;
    private int      rent;
    private int      position;
    private int      knowledge;

    /**
     * The Onable class represent a tile that can be owned by a player.
     * @param position ownable tile position
     * @param name     ownable tile name
     * @param rent     the cost of renting this tile
     * @param buy    the cost to purchase this tile
     * */

    public Ownable(int position, String name,int buy, int rent, int knowledge) {
        this.position = position;
        this.name = name;
        this.buy = buy;
        this.rent = rent;
        this.knowledge = knowledge;
    }

    public void onAction(Player p, Board b) {

        p.increaseKnowledge(knowledge);
        if (owner != null) {

            boolean success = p.pay(rent);

            message = p.getmName() + " paid the rent to " + owner.getmName() +
                    " and has " + p.getMoney() + " study-time left";

            if (success) {
                owner.increaseMoney(rent);
            } else {
                owner.increaseMoney(p.getMoney());
                p.pay(p.getMoney());
                message = p.getmName() + " Could not afford to pay the rent and has lost";
            }

        }

    }

    public boolean buyTile(Player p) {
        return p.buyTile(this);
    }
    public boolean hasOwner() {
        return owner != null;
    }

    public int getPurchaseCost() {
        return this.buy;
    }
    public int getRentCost() {
        return rent;
    }
    public int getPosition() {
        return position;
    }

    public String getMessage() {
        return message;
    }
    public String getName() {
        return name;
    }

}
