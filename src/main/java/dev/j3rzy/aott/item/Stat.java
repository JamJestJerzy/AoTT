package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.Stats;

public class Stat {
    public Stats stat;
    public double value;

    public double maxValue;
    public double currentValue;

    public Stat(Stats stat, double value) {
        this.stat = stat;
        this.value = value;
    }

    public Stat(Stats stat, double maxValue, double currentValue) {
        this.stat = stat;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
    }

    public void setValue(double value) { this.value = value; }
    public void setMaxValue(double maxValue) { this.maxValue = maxValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }
}