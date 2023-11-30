package dev.j3rzy.aott.items;

import dev.j3rzy.aott.abilities.InstantTransmission;
import dev.j3rzy.aott.abilities.WitherImpact;
import dev.j3rzy.aott.enums.*;
import dev.j3rzy.aott.item.Gemstone;
import dev.j3rzy.aott.item.GemstoneSlot;
import dev.j3rzy.aott.item.MagicWeapon;
import dev.j3rzy.aott.item.Stat;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.List;

public class Hyperion extends MagicWeapon {
    public Hyperion() {
        super(
            Material.IRON_SWORD,
            Type.SWORD, Rarity.LEGENDARY,
            "Hyperion",
            List.of(new String[]{
                ChatColor.GRAY + "Deals +" + ChatColor.GREEN + "50% " + ChatColor.GRAY + "damage to",
                ChatColor.GRAY + "Withers. Grants " + ChatColor.RED + "+1 Damage",
                ChatColor.GRAY + "and " + ChatColor.GREEN + "+2 " + ChatColor.AQUA + "Intelligence",
                ChatColor.GRAY + "per " + ChatColor.RED + "Catacombs " + ChatColor.GRAY + "level."
            }),
            List.of(new Stat[]{
                new Stat(Stats.GEAR_SCORE, 615),
                new Stat(Stats.DAMAGE, 260),
                new Stat(Stats.STRENGTH, 150),
                new Stat(Stats.INTELLIGENCE, 350),
                new Stat(Stats.FEROCITY, 30)
            }),
            List.of(new GemstoneSlot[]{
                new GemstoneSlot(GemstoneSlots.SAPPHIRE, true, new Gemstone(Gemstones.SAPPHIRE, Rarity.LEGENDARY)),
                new GemstoneSlot(GemstoneSlots.COMBAT, true, new Gemstone(Gemstones.SAPPHIRE, Rarity.LEGENDARY))
            }),
            true,
            new WitherImpact()
        );
    }
}
