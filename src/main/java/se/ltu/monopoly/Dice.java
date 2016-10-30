package se.ltu.monopoly;

import java.util.Random;

public class Dice {


    private Random random;
    private int sides;

    /**
     * @param sides is the number on dice sides.
     */
    public Dice(int sides) {

        if (sides <= 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.sides = sides;
        this.random = new Random();

    }

    /**
     * @return a random int between 0 - sides
     */
    public int roll() {
        return random.nextInt((sides - 1) + 1) + 1;
    }
}