package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.NewPlayer;


public interface Tile {
    public void stepInside(NewPlayer p, Board b);

    public int      getGetOrPay();
    public int      getBuyCoast();
    public int      getRentCoast();
    public int      getKnowledge();
    public int      getOwner();
    public boolean  isOwnable();
    public boolean  isChanceTile();
    public String   getName();
    public void     setOwner(int owner);
}
