package dev.j3rzy.aott.commands;

import dev.j3rzy.aott.items.AspectOfTheEnd;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class aote implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        player.getInventory().addItem(new AspectOfTheEnd().getItem());

        return true;
    }
}
