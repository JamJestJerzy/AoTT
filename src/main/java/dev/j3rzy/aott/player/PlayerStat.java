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

    /**
     *
     * @return Stat
     */
    public Stats getStat() { return stat; }

    /**
     *
     * @return amount of stat boost
     */
    public double getValue() { return value; }

    /**
     *
     * @return maximum stat's value
     */
    public double getMaxValue() { return maxValue; }

    /**
     *
     * @return whatever stat should regenerate or not
     */
    public boolean isRegenerating() { return regenerating; }

    /**
     *
     * @param value value to set to value to
     */
    @Override public void setValue(double value) { this.value = value; }

    /**
     *
     * @param maxValue value to set to maxValue to
     */
    public void setMaxValue(double maxValue) { this.maxValue = maxValue; }

    /**
     *
     * @param regenerating whatever stat can regenerate or not
     */
    public void setRegenerating(boolean regenerating) { this.regenerating = regenerating; }
}
