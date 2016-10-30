package se.ltu.monopoly.Tiles.ownable;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.Player;


public class Classroom extends Ownable {


    protected String name;
    protected int position;
    private int knowledge;

    public Classroom(int position,String name, int rent, int knowledge, int price) {
        super(position, name, rent, price);
        this.knowledge = knowledge;
        this.position = position;
        this.name = name;
    }

    @Override
    public void onAction(Player p, Board b) {
        super.onAction(p,b);
        p.increaseKnowledge(this.knowledge);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String message() {
        return "";
    }

}
