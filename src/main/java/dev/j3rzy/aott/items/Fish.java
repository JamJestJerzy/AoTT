package dev.j3rzy.aott.items;

import dev.j3rzy.aott.abilities.WaterImpact;
import dev.j3rzy.aott.abilities.WitherImpact;
import dev.j3rzy.aott.enums.Rarity;
import dev.j3rzy.aott.enums.Type;
import dev.j3rzy.aott.item.MagicWeapon;
import org.bukkit.Material;

import java.util.List;

public class Fish extends MagicWeapon {
    public Fish() {
        super(
            Material.COD,
            Type.NOTHING, Rarity.COMMON,
            "Fish",
            List.of(),
            List.of(),
            List.of(),
            false,
            new WaterImpact()
        );
    }
}
