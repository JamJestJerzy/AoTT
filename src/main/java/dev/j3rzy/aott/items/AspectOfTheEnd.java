package dev.j3rzy.aott.items;

import dev.j3rzy.aott.enums.Rarity;
import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.abilities.InstantTransmission;
import dev.j3rzy.aott.enums.Type;
import dev.j3rzy.aott.item.*;
import org.bukkit.Material;

import java.util.List;

public class AspectOfTheEnd extends MagicWeapon {
    public AspectOfTheEnd() {
        super(
            Material.DIAMOND_SWORD,
            Type.SWORD, Rarity.RARE,
            "Aspect of the End",
            List.of(new String[]{}),
            List.of(new Stat[]{
                new Stat(Stats.DAMAGE, 100),
                new Stat(Stats.STRENGTH, 100)
            }),
            true,
            new InstantTransmission()
        );
    }
}
