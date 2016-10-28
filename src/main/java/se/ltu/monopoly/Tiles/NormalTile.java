package se.ltu.monopoly.Tiles;

import se.ltu.monopoly.Board;
import se.ltu.monopoly.NewPlayer;
import se.ltu.monopoly.Player;


public class NormalTile implements Tile {

    private String  mName;
    private boolean mOwnable;
    private boolean isChanceTile;
    private int     mRentCost;
    private int     mBuyCost;
    private int     mKnowledge;
    private int     getOrPay;
    private int     mOwner; // -1 not owned

    public NormalTile(int getOrPay, int buyCost, int rentCost, int knowledge, String name, boolean ownable) {
        this.getOrPay       = getOrPay;
        this.mBuyCost       = buyCost;
        this.mRentCost      = rentCost;
        this.mKnowledge     = knowledge;
        this.mName          = name;
        this.mOwnable       = ownable;
        this.mOwner         = -1;
    }

    public void stepInside(NewPlayer player, Board board) {
        if(mOwner >= 0){
        // rent cost is handled inside send money
            NewPlayer owner = board.getPlayer(mOwner);
            owner.setMoney(owner.getMoney()+ mRentCost);
            player.setMoney(player.getMoney()- mRentCost);
        }

        player.setMoney(player.getMoney()+ getOrPay);
    }
    public void setIsChanceTile(boolean isChanceTile){
        this.isChanceTile = isChanceTile;
    }

    public boolean isChanceTile(){
        return isChanceTile;
    }

    public int getGetOrPay() {
        return getOrPay;
    }

    public int getBuyCoast() {
        return mBuyCost;
    }

    public int getRentCoast() {
        return mRentCost;
    }

    public int getKnowledge() {
        return mKnowledge;
    }

    public int getOwner() {
        return mOwner;
    }

    public boolean isOwnable() {
        return mOwnable;
    }

    public String getName() {
        return mName;
    }

    public void setOwner(int owner) {
        mOwner = owner;
    }

}
