package dev.j3rzy.aott.player;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Stat;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final org.bukkit.entity.Player player;

    private final List<Stat> stats = new ArrayList<>();

    public Player(org.bukkit.entity.Player player) {
        this.player = player;

        stats.add(new Stat(Stats.HEALTH, 100));
        stats.add(new Stat(Stats.DEFENSE, 0));
        stats.add(new Stat(Stats.STRENGTH, 0));
        stats.add(new Stat(Stats.INTELLIGENCE, 100));
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
    }

    public org.bukkit.entity.Player getPlayer() {
        return player;
    }

    public void updateStat(Stats statistic, double newValue) {
        for (Stat stat : stats) if (stat.stat == statistic) stat.setValue(newValue);
    }

    public Stat getStat(Stats statistic) {
        for (Stat stat : stats) if (stat.stat == statistic) return stat;
        return null;
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