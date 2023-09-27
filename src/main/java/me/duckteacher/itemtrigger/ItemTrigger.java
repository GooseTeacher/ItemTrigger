package me.duckteacher.itemtrigger;

import me.duckteacher.itemtrigger.command.ItemTriggerCommand;
import me.duckteacher.itemtrigger.command.ItreloadCommand;
import me.duckteacher.itemtrigger.file.DataManager;
import me.duckteacher.itemtrigger.listener.TriggerListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ItemTrigger extends JavaPlugin {
    public static ItemTrigger instance;
    public static Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        logger = getLogger();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new TriggerListener(), this);

        //<editor-fold desc="Commands">
        PluginCommand var = getCommand("itemtrigger");
        if (var != null) {
            var.setTabCompleter(new ItemTriggerCommand());
            var.setExecutor(new ItemTriggerCommand());
        }
        var = getCommand("itreload");
        if (var != null) {
            var.setTabCompleter(new ItreloadCommand());
            var.setExecutor(new ItreloadCommand());
        }
        //</editor-fold>

        DataManager.load();
    }

    @Override
    public void onDisable() {
        DataManager.save();
    }

    public static ItemTrigger getInstance() {
        return instance;
    }
}
