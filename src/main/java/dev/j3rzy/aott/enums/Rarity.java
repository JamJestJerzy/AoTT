package dev.j3rzy.aott.enums;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON("COMMON", ChatColor.WHITE),
    UNCOMMON("UNCOMMON", ChatColor.GREEN),
    RARE("RARE", ChatColor.BLUE),
    EPIC("EPIC", ChatColor.DARK_PURPLE),
    LEGENDARY("LEGENDARY", ChatColor.GOLD),
    MYTHIC("MYTHIC", ChatColor.LIGHT_PURPLE),
    DIVINE("DIVINE", ChatColor.AQUA),
    SPECIAL("SPECIAL", ChatColor.RED),
    VERY_SPECIAL("VERY SPECIAL", ChatColor.RED),
    ADMIN("ADMIN", ChatColor.DARK_RED);

    /**
     * Rarity's display name
     */
    public final String name;
    /**
     * Rarity's color
     */
    public final ChatColor color;

    Rarity(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }
}