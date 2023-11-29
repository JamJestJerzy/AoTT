package dev.j3rzy.aott;

import static dev.j3rzy.aott.Utils.log;
import static dev.j3rzy.aott.Utils.pm;

import dev.j3rzy.aott.commands.aote;
import dev.j3rzy.aott.commands.hyperion;
import dev.j3rzy.aott.events.EntityDamageByEntity;
import dev.j3rzy.aott.events.PlayerInteract;
import dev.j3rzy.aott.events.PlayerJoin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Aott extends JavaPlugin {
    @Override
    public void onEnable() {
        /* Register events */
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new EntityDamageByEntity(), this);

        /* Register commands */
        Objects.requireNonNull(this.getCommand("aote")).setExecutor(new aote());
        Objects.requireNonNull(this.getCommand("hyperion")).setExecutor(new hyperion());

        log.info("Plugin got enabled!");
    }

    @Override
    public void onDisable() {
        log.info("Plugin got disabled.");
    }
}
