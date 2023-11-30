package dev.j3rzy.aott.enums;

import org.bukkit.ChatColor;

public enum Gemstones {
    RUBY(ChatColor.RED),
    AMETHYST(ChatColor.DARK_PURPLE),
    JADE(ChatColor.GREEN),
    SAPPHIRE(ChatColor.AQUA),
    AMBER(ChatColor.GOLD),
    TOPAZ(ChatColor.YELLOW),
    JASPER(ChatColor.LIGHT_PURPLE);

    public final ChatColor color;

    Gemstones(ChatColor color) {
        this.color = color;
    }
}
