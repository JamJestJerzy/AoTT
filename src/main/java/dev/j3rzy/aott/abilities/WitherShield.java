package dev.j3rzy.aott.abilities;

import dev.j3rzy.aott.enums.Stats;
import dev.j3rzy.aott.item.Ability;
import dev.j3rzy.aott.players.Players;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static dev.j3rzy.aott.utils.WorldUtils.getMobsInRadius;

public class WitherShield extends Ability {
    private static final String name = "Wither Shield";
    private static final List<String> description = List.of(new String[]{
        ChatColor.GRAY + "Reduces damage taken by " + ChatColor.RED + "10%",
        ChatColor.GRAY + "for " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds. Also grants",
        ChatColor.GRAY + "an absorption shield that gives",
        Stats.CRIT_DAMAGE.color + "150% " + ChatColor.GRAY + "of your Critical Damage",
        ChatColor.GRAY + "as health, after " + ChatColor.YELLOW + "5 " + ChatColor.GRAY + "seconds",
        ChatColor.GREEN + "50% " + ChatColor.GRAY + "of the shield is",
        ChatColor.GRAY + "converted into healing."
    });
    private static final double manaCost = 150;
    private static final double cooldown = 10;
    private static final List<Action> triggers = List.of(new Action[]{
        Action.RIGHT_CLICK_AIR,
        Action.RIGHT_CLICK_BLOCK
    });

    public WitherShield() {
        super(name, description, manaCost, cooldown, triggers);
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
        dev.j3rzy.aott.player.Player player = Players.INSTANCE.getPlayer(p);

        double absortionAmount = player.getStat(Stats.CRIT_DAMAGE).getValue() * 1.5;

        player.addDamageReduction(0.1);
        new java.util.Timer().schedule(new java.util.TimerTask(){@Override public void run(){
            player.removeDamageReduction(0.1);
        }},5000);
        player.addAbsorption(absortionAmount);
        new java.util.Timer().schedule(new java.util.TimerTask(){@Override public void run(){
            double absortionRemoved = Math.max(player.getAbsorptionAmount(), absortionAmount);
            player.removeAbsorption(absortionRemoved);
            player.heal(absortionRemoved * 0.5);
        }},5000);
    }
}
