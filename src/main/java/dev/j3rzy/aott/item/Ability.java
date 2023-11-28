package dev.j3rzy.aott.item;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Ability {
    public final String name;
    public final List<String> description;
    public final double manaCost;
    public final double cooldown;
    public final List<Action> triggers;

    public Ability(String name, List<String> description, double manaCost, double cooldown, List<Action> triggers) {
        this.name = name;
        this.description = description;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.triggers = triggers;
    }

    public boolean haveAbility(ItemStack item) { return false; }
    public void onTrigger(PlayerInteractEvent event) {}
}