package dev.j3rzy.aott.events;

import dev.j3rzy.aott.abilities.Abilities;
import dev.j3rzy.aott.item.Ability;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        for (Ability ability : Abilities.INSTANCE.getAbilities()) if (ability.haveAbility(item)) if (ability.triggers.contains(event.getAction())) ability.onTrigger(event);
    }
}
