package se.ltu.monopoly.Tiles.ownable;

/**
 * Created by erikuusitalo on 28/10/16.
 */
public class Stil extends Ownable {


    public Stil(int rent, int price) {
        super(rent, price);
    }

    @Override
    public String toString() {
        return "Stil";
    }
}
