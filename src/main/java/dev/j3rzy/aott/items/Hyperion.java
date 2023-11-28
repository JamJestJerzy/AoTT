package dev.j3rzy.aott.items;

import dev.j3rzy.aott.abilities.InstantTransmission;
import dev.j3rzy.aott.abilities.WitherImpact;
import dev.j3rzy.aott.enums.Rarity;
import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.enums.Type;
import dev.j3rzy.aott.item.MagicWeapon;
import dev.j3rzy.aott.item.Stat;
import org.bukkit.Material;

import java.util.List;

public class Hyperion extends MagicWeapon {
    public Hyperion() {
        super(
            Material.IRON_SWORD,
            Type.SWORD, Rarity.LEGENDARY,
            "Hyperion",
            List.of(new Stat[]{
                new Stat(Stats.GEAR_SCORE, 615),
                new Stat(Stats.DAMAGE, 260),
                new Stat(Stats.STRENGTH, 150),
                new Stat(Stats.INTELLIGENCE, 350),
                new Stat(Stats.FEROCITY, 30)
            }),
            new WitherImpact()
        );
    }
}
