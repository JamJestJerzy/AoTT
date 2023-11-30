package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.Stats;

public class Stat {
    public Stats stat;
    public double value;

    public Stat(Stats stat, double value) {
        this.stat = stat;
        this.value = value;
    }

    public void setValue(double value) { this.value = value; }
}