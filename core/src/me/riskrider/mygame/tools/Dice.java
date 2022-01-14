package me.riskrider.mygame.tools;

public class Dice {

    private int[] values; // Array of the value of each face of the dice.
    private int current; // The current dice value.

    /**
     * Constructs a dice with a range of numbers from a given min to a given max.
     * @param min Lowest number on the dice.
     * @param max Highest number on the dice.
     */
    public Dice(int min, int max) {
        int sideNum = max - min + 1;
        values = new int[sideNum];
        for (int i = 0; i < sideNum; i++) {
            values[i] = min + i;
        }
        current = values[0];
    }

    /**
     * Constructs a dice with a range of numbers from 1 to a given max.
     * @param max Largest number on the dice.
     */
    public Dice(int max) {
        values = new int[max];
        for (int i = 0; i < max; i++) {
            values[i] = i + 1;
        }
        current = values[0];
    }

    /**
     * Creates a dice with numbers from a given array.
     * @param vals The array containing the values of the faces of the dice.
     */
    public Dice(int[] vals) {
        values = vals;
        current = values[0];
    }

    /**
     * Rolls the dice and gives the new value.
     * @return The new dice value.
     */
    public int roll() {
        int i = (int) (Math.round(Math.random() * (values.length - 1)));
        current = values[i];
        return current;
    }

    /**
     * Gets the currecnt dice value
     * @return The current dice value.
     */
    public int getValue() {
        return current;
    }

}
