package dev.j3rzy.aott;

import static dev.j3rzy.aott.events.Scheduled.*;
import static dev.j3rzy.aott.utils.Utils.log;
import static dev.j3rzy.aott.utils.Utils.pm;

import dev.j3rzy.aott.commands.*;
import dev.j3rzy.aott.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.Objects;

public final class Aott extends JavaPlugin {
    @Override
    public void onEnable() {
        /* Register events */
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new EntityDamageByEntity(), this);
        pm.registerEvents(new EntityDamage(), this);
        pm.registerEvents(new EntityDamageByBlock(), this);
        pm.registerEvents(new PlayerRespawn(), this);

        /* Register commands */
        Objects.requireNonNull(this.getCommand("aote")).setExecutor(new aote());
        Objects.requireNonNull(this.getCommand("hyperion")).setExecutor(new hyperion());
        Objects.requireNonNull(this.getCommand("stick")).setExecutor(new stick());
        Objects.requireNonNull(this.getCommand("fish")).setExecutor(new fish());
        Objects.requireNonNull(this.getCommand("gigarion")).setExecutor(new gigarion());

        onceEveryTwoSeconds();
        onceASecond();
        onceOneForthOfASecond();

        log.info("Plugin got enabled!");
    }

    @Override
    public void onDisable() {
        log.info("Plugin got disabled.");
    }
}
