package dev.j3rzy.aott.item;

import dev.j3rzy.aott.enums.Gemstones;
import dev.j3rzy.aott.enums.Rarity;

public class Gemstone {
    private final Gemstones gemestone;
    private final Rarity gemstoneTier;

    public Gemstone(Gemstones gemestone, Rarity gemstoneTier) {
        this.gemestone = gemestone;
        this.gemstoneTier = gemstoneTier;
    }

    public Gemstones getGemestone() {
        return gemestone;
    }

    public Rarity getGemstoneTier() {
        return gemstoneTier;
    }
}
