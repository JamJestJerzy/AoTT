package dev.j3rzy.aott.player;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Stat;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final org.bukkit.entity.Player player;

    private final List<Stat> stats = new ArrayList<>();
    private double damageReduction;
    private double absorptionAmount;

    public Player(org.bukkit.entity.Player player) {
        this.player = player;

        stats.add(new Stat(Stats.HEALTH, 100,100));
        stats.add(new Stat(Stats.DEFENSE, 0));
        stats.add(new Stat(Stats.STRENGTH, 0));
        stats.add(new Stat(Stats.INTELLIGENCE, 100, 100));
        stats.add(new Stat(Stats.CRIT_DAMAGE, 50));
        stats.add(new Stat(Stats.CRIT_CHANCE, 30));
        stats.add(new Stat(Stats.FEROCITY, 0));
        stats.add(new Stat(Stats.BONUS_ATTACK_SPEED, 0));
        stats.add(new Stat(Stats.ABILITY_DAMAGE, 0));
        stats.add(new Stat(Stats.HEALTH_REGEN, 100));
        stats.add(new Stat(Stats.VITALITY, 100));
        stats.add(new Stat(Stats.MENDING, 100));
        stats.add(new Stat(Stats.TRUE_DEFENSE, 0));
        stats.add(new Stat(Stats.SWING_RANGE, 3));

        this.absorptionAmount = 0;
        this.damageReduction = 0;
    }

    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    public void updateStat(Stats statistic, double newValue) {
        if (statistic == Stats.HEALTH || statistic == Stats.INTELLIGENCE) {
            for (Stat stat : stats) if (stat.stat == statistic) stat.setMaxValue(newValue);
        }
        for (Stat stat : stats) if (stat.stat == statistic) stat.setValue(newValue);
    }

    public Stat getStat(Stats statistic) {
        for (Stat stat : stats) if (stat.stat == statistic) return stat;
        return null;
    }

    public double getDamageReduction() {return damageReduction; }
    public void addDamageReduction(double amount) { damageReduction += amount; }
    public void removeDamageReduction(double amount) { damageReduction -= Math.min(amount, damageReduction); }
    public void setDamageReduction(double damageReduction) { this.damageReduction = damageReduction; }

    public double getAbsorptionAmount() { return absorptionAmount; }
    public void addAbsorption(double amount) { absorptionAmount += amount; }
    public void removeAbsorption(double amount) { absorptionAmount -= amount; }
    public void setAbsorptionAmount(double absorptionAmount) { this.absorptionAmount = absorptionAmount; }

    public void dealDamage(double amount) {
        double playerDefence = getStat(Stats.DEFENSE).value;
        double finalDamage = (amount * (playerDefence / (playerDefence + 100))) * getDamageReduction();
        if (absorptionAmount > 0) {
            double damageToAbsorptionShield = Math.min(finalDamage, absorptionAmount);
            absorptionAmount -= damageToAbsorptionShield;
            finalDamage -= damageToAbsorptionShield;
        }
        if (finalDamage > 0) getStat(Stats.HEALTH).setCurrentValue(getStat(Stats.HEALTH).value - finalDamage);
    }

    public void heal(double amount) {
        Stat health = getStat(Stats.HEALTH);
        if (health.currentValue >= health.maxValue) return;
        health.setCurrentValue(Math.min(amount, health.maxValue));
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