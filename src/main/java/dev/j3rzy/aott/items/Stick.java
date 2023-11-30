package dev.j3rzy.aott.items;

import dev.j3rzy.aott.abilities.WitherImpact;
import dev.j3rzy.aott.enums.Rarity;
import dev.j3rzy.aott.enums.Type;
import dev.j3rzy.aott.item.MagicWeapon;
import org.bukkit.Material;

import java.util.List;

public class Stick extends MagicWeapon {
    public Stick() {
        super(
            Material.STICK,
            Type.NOTHING, Rarity.COMMON,
            "Stick",
            List.of(),
            List.of(),
            false,
            new WitherImpact()
        );
    }
}
