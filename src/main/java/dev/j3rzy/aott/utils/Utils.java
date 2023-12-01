package dev.j3rzy.aott.utils;

import dev.j3rzy.aott.Aott;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Utils {
    /**
     * Logger
     */
    public static Logger log = Logger.getLogger("AOTT");
    /**
     * plugin main class
     */
    public static JavaPlugin plugin = JavaPlugin.getPlugin(Aott.class);
    /**
     * Server's plugin manager
     */
    public static PluginManager pm = plugin.getServer().getPluginManager();
}
