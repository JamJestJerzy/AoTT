package dev.j3rzy.aott.enums;

public enum Stats {
    HEALTH("Health"),
    DEFENSE("Defense"),
    STRENGTH("Strength"),
    INTELLIGENCE("Intelligence"),
    CRIT_CHANCE("Crit Chance"),
    CRIT_DAMAGE("Crit Damage"),
    BONUS_ATTACK_SPEED("Bonus Attack Speed"),
    ABILITY_DAMAGE("Ability Damage"),
    TRUE_DEFENSE("True Defense"),
    FEROCITY("Ferocity"),
    HEALTH_REGEN("Health Regen"),
    VITALITY("Vitality"),
    MENDING("Mending"),
    SWING_RANGE("Swing Range"),

    DAMAGE("Damage"),
    GEAR_SCORE("Gear Score");

    public final String name;

    Stats(String name) { this.name = name; }
}