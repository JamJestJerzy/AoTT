package dev.j3rzy.aott.abilities;

import dev.j3rzy.aott.item.Ability;

import java.util.ArrayList;
import java.util.List;

public class Abilities {
    public static final Abilities INSTANCE = new Abilities();
    private final List<Ability> abilities = new ArrayList<>();

    public Abilities() {
        registerAbilities();
    }

    private void registerAbilities() {
        abilities.add(new InstantTransmission());
        abilities.add(new WitherImpact());
        abilities.add(new WitherShield());
    }

    public List<Ability> getAbilities() {
        return abilities;
    }
}
