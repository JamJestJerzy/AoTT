package dev.j3rzy.aott.enums;

import org.bukkit.ChatColor;

public enum Gemstones {
    RUBY(ChatColor.RED, Stats.HEALTH, 99),
    AMETHYST(ChatColor.DARK_PURPLE, Stats.DEFENSE, 99),
    JADE(ChatColor.GREEN, Stats.MENDING, 99), //tmp
    SAPPHIRE(ChatColor.AQUA, Stats.INTELLIGENCE, 99),
    AMBER(ChatColor.GOLD, Stats.MENDING, 99), //tmp
    TOPAZ(ChatColor.YELLOW, Stats.MENDING, 99), //tmp
    JASPER(ChatColor.LIGHT_PURPLE, Stats.STRENGTH, 99);

    /**
     * Gemstone's color
     */
    public final ChatColor color;
    /**
     * Stat gemstone gives boost of
     */
    public final Stats stat;
    /**
     * Amount of stat boost gemstone gives
     */
    public final int amount;

    Gemstones(ChatColor color, Stats stat, double amount) {
        this.color = color;
        this.stat = stat;
        this.amount = (int)amount;
    }
}
