package dev.j3rzy.aott.player;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Stat;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final org.bukkit.entity.Player player;

    private final List<PlayerStat> stats = new ArrayList<>();
    private double damageReduction;
    private double absorptionAmount;

    public Player(org.bukkit.entity.Player player) {
        this.player = player;

        stats.add(new PlayerStat(Stats.HEALTH, 100,100, true));
        stats.add(new PlayerStat(Stats.DEFENSE, 0, -1, false));
        stats.add(new PlayerStat(Stats.STRENGTH, 0, -1, false));
        stats.add(new PlayerStat(Stats.INTELLIGENCE, 100, 100, true));
        stats.add(new PlayerStat(Stats.CRIT_DAMAGE, 50, -1, false));
        stats.add(new PlayerStat(Stats.CRIT_CHANCE, 30, 100, false));
        stats.add(new PlayerStat(Stats.FEROCITY, 0, 500, false));
        stats.add(new PlayerStat(Stats.BONUS_ATTACK_SPEED, 0, 100, false));
        stats.add(new PlayerStat(Stats.ABILITY_DAMAGE, 0, -1, false));
        stats.add(new PlayerStat(Stats.HEALTH_REGEN, 100, -1, false));
        stats.add(new PlayerStat(Stats.VITALITY, 100, -1, false));
        stats.add(new PlayerStat(Stats.MENDING, 100, -1, false));
        stats.add(new PlayerStat(Stats.TRUE_DEFENSE, 0, -1, false));
        stats.add(new PlayerStat(Stats.SWING_RANGE, 3, -1, false));

        this.absorptionAmount = 0;
        this.damageReduction = 0;
    }

    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    public void updateStat(Stats statistic, double newValue) {
        for (PlayerStat playerStat : stats) if (playerStat.getStat() == statistic) {
            if (playerStat.isRegenerating()) playerStat.setMaxValue(newValue);
            else {
                if (playerStat.getMaxValue() == -1) playerStat.setValue(newValue);
                else playerStat.setValue(Math.min(newValue, playerStat.getMaxValue()));
            }
        }
    }

    public PlayerStat getStat(Stats statistic) {
        for (PlayerStat playerStat : stats) if (playerStat.getStat() == statistic) return playerStat;
        return null;
    }

    public double getDamageReduction() {return damageReduction; }
    public void addDamageReduction(double amount) { damageReduction += amount; }
    public void removeDamageReduction(double amount) { damageReduction -= Math.min(amount, damageReduction); }
    public void setDamageReduction(double damageReduction) { this.damageReduction = damageReduction; }

    public double getAbsorptionAmount() { return absorptionAmount; }
    public void addAbsorption(double amount) { absorptionAmount += amount; }
    public void removeAbsorption(double amount) { absorptionAmount = Math.max(0, absorptionAmount - amount); }
    public void setAbsorptionAmount(double absorptionAmount) { this.absorptionAmount = absorptionAmount; }

    public void dealDamage(double amount) {
        PlayerStat health = getStat(Stats.HEALTH);
        double playerDefence = getStat(Stats.DEFENSE).getValue();
        double finalDamage = amount * (1 - ((playerDefence > 0) ? (playerDefence / (playerDefence + 100)) : 1)) * (1-getDamageReduction());
        if (absorptionAmount > 0) {
            double damageToAbsorptionShield = Math.min(finalDamage, absorptionAmount);
            absorptionAmount -= damageToAbsorptionShield;
            finalDamage -= damageToAbsorptionShield;
        }
        if (finalDamage > 0) health.setValue(Math.max(0, health.getValue() - finalDamage));
    }

    public void heal(double amount) {
        PlayerStat health = getStat(Stats.HEALTH);
        if (health.getValue() >= health.getMaxValue()) return;
        health.setValue(Math.min(amount, health.getMaxValue()));
    }
}

/*
    private Stat health;
    private Stat defense;
    private Stat strength;
    private Stat intelligence;
    private Stat critChance;
    private Stat critDamage;
    private Stat bonusAttackSpeed;
    private Stat abilityDamage;
    private Stat healthRegen;
    private Stat vitality;
    private Stat mending;
    private Stat trueDefence;
    private Stat swingRange;

    this.health = new Stat(Stats.HEALTH, 100);
    this.defense = new Stat(Stats.DEFENSE, 0);
    this.strength = new Stat(Stats.STRENGTH, 0);
    this.intelligence = new Stat(Stats.INTELLIGENCE, 100);
    */