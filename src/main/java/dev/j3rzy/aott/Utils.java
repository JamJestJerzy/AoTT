package dev.j3rzy.aott;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Utils {
    public static Logger log = Logger.getLogger("AOTT");
    public static JavaPlugin plugin = JavaPlugin.getPlugin(Aott.class);
    public static PluginManager pm = plugin.getServer().getPluginManager();
}
