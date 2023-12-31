package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.Stats;

public class Stat {
    public Stats stat;
    public double value;
    private final double baseValue;

    public Stat(Stats stat, double value) {
        this.stat = stat;
        this.value = value;
        this.baseValue = value;
    }

    /**
     *
     * @param value amout of stat
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Restores base value
     */
    public void resetValue() {
        this.value = baseValue;
    }
}