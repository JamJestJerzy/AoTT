package dev.j3rzy.aott.enums;

import org.bukkit.ChatColor;

public enum Stats {
    HEALTH("Health", "\u2764", ChatColor.RED, ChatColor.GREEN),
    DEFENSE("Defense", "\u2748", ChatColor.GREEN, ChatColor.GREEN),
    STRENGTH("Strength", "\u2741", ChatColor.RED, ChatColor.RED),
    INTELLIGENCE("Intelligence", "\u270e", ChatColor.AQUA, ChatColor.GREEN),
    CRIT_CHANCE("Crit Chance", "\u2620", ChatColor.BLUE, ChatColor.RED),
    CRIT_DAMAGE("Crit Damage", "\u2623", ChatColor.BLUE, ChatColor.RED),
    BONUS_ATTACK_SPEED("Bonus Attack Speed", "\u2694", ChatColor.YELLOW, ChatColor.RED),
    ABILITY_DAMAGE("Ability Damage", "\u0e51", ChatColor.RED, ChatColor.RED),
    TRUE_DEFENSE("True Defense", "\u2742", ChatColor.WHITE, ChatColor.GREEN),
    FEROCITY("Ferocity", "\u2AFD", ChatColor.RED, ChatColor.GREEN),
    HEALTH_REGEN("Health Regen", "\u2763", ChatColor.RED, ChatColor.GREEN),
    VITALITY("Vitality", "\u2668", ChatColor.DARK_RED, ChatColor.GREEN),
    MENDING("Mending", "\u2604", ChatColor.GREEN, ChatColor.GREEN),
    SWING_RANGE("Swing Range", "\u24c8", ChatColor.YELLOW, ChatColor.GREEN),

    DAMAGE("Damage", "", ChatColor.RED, ChatColor.RED),
    GEAR_SCORE("Gear Score", "", ChatColor.GRAY, ChatColor.LIGHT_PURPLE);

    /**
     * Stat's name
     */
    public final String name;
    /**
     * Stat's symbol
     */
    public final String symbol;
    /**
     * Stat's name color
     */
    public final ChatColor color;
    /**
     * Stat's value color
     */
    public final ChatColor valueColor;

    Stats(String name, String symbol, ChatColor color, ChatColor valueColor) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.valueColor = valueColor;
    }

    public static Stats getStatByName(String name) {
        for (Stats stat : Stats.values()) {
            if (stat.name.equalsIgnoreCase(name)) {
                return stat;
            }
        }
        return null;
    }
}