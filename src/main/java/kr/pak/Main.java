package kr.pak;

import kr.pak.reality.time.RealTimeCommand;
import kr.pak.reality.time.RealTimeMain;
import kr.pak.skill.Berserker;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    Logger logger = getLogger();
    PluginManager pluginManager = getServer().getPluginManager();

    File file = new File("plugins/HappyNewYear/config.yml");
    File directory = new File("plugins/HappyNewYear");

    @Override
    public void onEnable() {
        try {
            if (!(directory.exists())) {
                directory.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileConfiguration configFile = YamlConfiguration.loadConfiguration(file);
            configFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addEvents();
        addCommands();
        logger.info("HappyNewYear Plugin Enabled");

        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        new RealTimeMain().SetTime(logger);
    }

    @Override
    public void onDisable() {
        logger.info("HappyNewYear Plugin Disabled");

        try {
            FileConfiguration configFile = YamlConfiguration.loadConfiguration(file);
            configFile.set(this.getName(), "Test");
            configFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEvents() {
        // pluginManager.registerEvents(new Berserker(), this);
    }


    public void addCommands() {
        getCommand("realtime").setExecutor(new RealTimeCommand());
    }
}
