package dev.j3rzy.aott.commands;

import dev.j3rzy.aott.items.AspectOfTheEnd;
import dev.j3rzy.aott.items.Fish;
import dev.j3rzy.aott.items.Items;
import dev.j3rzy.aott.items.Stick;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        player.getInventory().addItem(Items.INSTANCE.getItem(new Fish()).getItem());

        return true;
    }
}
