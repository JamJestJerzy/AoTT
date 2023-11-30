package dev.j3rzy.aott.enums;

import org.bukkit.ChatColor;

public enum Stats {
    HEALTH("Health", ChatColor.RED, ChatColor.GREEN),
    DEFENSE("Defense", ChatColor.GREEN, ChatColor.GREEN),
    STRENGTH("Strength", ChatColor.RED, ChatColor.RED),
    INTELLIGENCE("Intelligence", ChatColor.AQUA, ChatColor.GREEN),
    CRIT_CHANCE("Crit Chance", ChatColor.BLUE, ChatColor.RED),
    CRIT_DAMAGE("Crit Damage", ChatColor.BLUE, ChatColor.RED),
    BONUS_ATTACK_SPEED("Bonus Attack Speed", ChatColor.YELLOW, ChatColor.RED),
    ABILITY_DAMAGE("Ability Damage", ChatColor.RED, ChatColor.RED),
    TRUE_DEFENSE("True Defense", ChatColor.WHITE, ChatColor.GREEN),
    FEROCITY("Ferocity", ChatColor.RED, ChatColor.GREEN),
    HEALTH_REGEN("Health Regen", ChatColor.RED, ChatColor.GREEN),
    VITALITY("Vitality", ChatColor.DARK_RED, ChatColor.GREEN),
    MENDING("Mending", ChatColor.GREEN, ChatColor.GREEN),
    SWING_RANGE("Swing Range", ChatColor.YELLOW, ChatColor.GREEN),

    DAMAGE("Damage", ChatColor.RED, ChatColor.RED),
    GEAR_SCORE("Gear Score", ChatColor.GRAY, ChatColor.LIGHT_PURPLE);

    public final String name;
    public final ChatColor color;
    public final ChatColor valueColor;

    Stats(String name, ChatColor color, ChatColor valueColor) {
        this.name = name;
        this.color = color;
        this.valueColor = valueColor;
    }
}