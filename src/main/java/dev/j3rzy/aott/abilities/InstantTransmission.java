package dev.j3rzy.aott.abilities;

import dev.j3rzy.aott.item.Ability;
import dev.j3rzy.aott.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class InstantTransmission extends Ability {
    private static final String name = "Instant Transmission";
    private static final List<String> description = List.of(new String[]{
        ChatColor.GRAY + "Teleport " + ChatColor.GREEN + "8 blocks " + ChatColor.GRAY + "ahead of",
        ChatColor.GRAY + "you and gain " + ChatColor.GREEN + "+50 " + ChatColor.WHITE + "Speed",
        ChatColor.GRAY + "for " + ChatColor.GREEN + "3 seconds" + ChatColor.GRAY + "."
    });
    private static final double manaCost = 50;
    private static final List<Action> triggers = List.of(new Action[]{
        Action.RIGHT_CLICK_AIR,
        Action.RIGHT_CLICK_BLOCK
    });

    public InstantTransmission() {
        super(name, description, manaCost, 0, triggers);
    }

    @Override
    public boolean haveAbility(ItemStack item) {
        if (item != null && item.getItemMeta() != null && item.getItemMeta().getLore() != null)
            for (String line : item.getItemMeta().getLore())
                if (line.contains("Ability: " + name)) return true;
        return false;
    }

    @Override
    public void onTrigger(PlayerInteractEvent event) {
        Player p = event.getPlayer();

        PlayerUtils.teleportXBlocksForward(p, 8);
    }
}
