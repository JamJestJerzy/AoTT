package dev.j3rzy.aott.player;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Stat;

public class PlayerStat extends Stat {
    private final Stats stat;
    private double value;
    private double maxValue;
    private boolean regenerating;

    public PlayerStat(Stats stat, double value, double maxValue, boolean regenerating) {
        super(stat, value);

        this.stat = stat;
        this.value = value;
        this.maxValue = maxValue;
        this.regenerating = regenerating;
    }

    public Stats getStat() { return stat; }
    public double getValue() { return value; }
    public double getMaxValue() { return maxValue; }
    public boolean isRegenerating() { return regenerating; }
    @Override public void setValue(double value) { this.value = value; }
    public void setMaxValue(double maxValue) { this.maxValue = maxValue; }
    public void setRegenerating(boolean regenerating) { this.regenerating = regenerating; }
}
