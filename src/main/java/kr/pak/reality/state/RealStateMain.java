package kr.pak.reality.state;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class RealStateMain implements Listener {

    File file = new File("plugins/HappyNewYear/config.yml");
    FileConfiguration configFile = YamlConfiguration.loadConfiguration(file);

    HashMap<Player, Float> ThirstState = new HashMap<>();
    HashMap<Player, Float> HungerState = new HashMap<>();

    @EventHandler
    public void OnJoin(PlayerJoinEvent e) {
        if (!(e.getPlayer().hasPlayedBefore())) {
            ThirstState.put(e.getPlayer(), 100f);
            HungerState.put(e.getPlayer(), 100f);
        } else {
            Float TS = (Float) configFile.get(String.valueOf(e.getPlayer()));
            Float HS = (Float) configFile.get(String.valueOf(e.getPlayer()));
            if (TS.equals(null) || TS.equals("") || HS.equals(null) || HS.equals("")) {
                ThirstState.put(e.getPlayer(), 100f);
                HungerState.put(e.getPlayer(), 100f);
            } else {
                ThirstState.put(e.getPlayer(), (Float) configFile.get(String.valueOf(e.getPlayer())));
                HungerState.put(e.getPlayer(), (Float) configFile.get(String.valueOf(e.getPlayer())));
            }
        }
    }

    public void SaveState() {
        try {
            for (Player players : ThirstState.keySet()) {
                configFile.set(String.valueOf(players), ThirstState.get(players));
            }

            for (Player players : HungerState.keySet()) {
                configFile.set(String.valueOf(players), HungerState.get(players));
            }

            configFile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
