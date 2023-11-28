package dev.j3rzy.aott.commands;

import dev.j3rzy.aott.items.Hyperion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hyperion implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        player.getInventory().addItem(new Hyperion().getItem());

        return true;
    }
}
