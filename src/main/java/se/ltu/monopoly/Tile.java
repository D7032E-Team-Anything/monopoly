package se.ltu.monopoly;

/**
 * Created by haidar on 2016-10-27.
 */
public class Tile {

    private int get, pay, buy, rent, knowledge;
    String  name;

    public Tile(int get, int pay, int buy, int rent, int knowledge, String name) {
        this.get = get;
        this.pay = pay;
        this.buy = buy;
        this.rent = rent;
        this.knowledge = knowledge;
        this.name = name;
    }

    public int getGet() {
        return get;
    }

    public int getPay() {
        return pay;
    }

    public int getBuy() {
        return buy;
    }

    public int getRent() {
        return rent;
    }

    public int getknowledge() {
        return knowledge;
    }

    public String getName() {
        return name;
    }

}
