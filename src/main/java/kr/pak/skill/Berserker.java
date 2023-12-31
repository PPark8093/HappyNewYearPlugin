package kr.pak.skill;

import kr.pak.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.Map;

//import static org.bukkit.Color.*;

public class Berserker implements Listener {

    Map<Player, Integer> deathCount = new HashMap<>();

    @EventHandler
    public void OnJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        deathCount.put(player, 0);
        player.sendMessage(ChatColor.RED + "CLASS: BERSERKER");
        player.sendMessage(ChatColor.GRAY + "DESCRIPTION: 죽을수록 강해집니다!");
    }

    @EventHandler
    public void OnRespawnEvent(PlayerRespawnEvent e) {
        Player player = e.getPlayer();

        if (deathCount.get(player) < 5) {
            deathCount.put(player, deathCount.get(player) + 1);
            player.sendMessage(ChatColor.GRAY + "DEATHCOUNT: " + deathCount.get(player));
        } else {

            Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("Arena"), task -> {

                if (deathCount.get(player).equals(Integer.MAX_VALUE)) {
                    player.sendMessage(ChatColor.RED + "몸이 원래대로 돌아오기 시작합니다!");
                    deathCount.put(player, 0);

                } else {
                    player.sendMessage(ChatColor.DARK_RED + "최대치를 초과하였습니다!");
                    player.sendMessage(ChatColor.DARK_GRAY + "몸이 쇠약해지기 시작합니다");


                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1, false, false));
                    player.sendMessage("효과 부여 완료");
                    deathCount.put(player, Integer.MAX_VALUE);
                }
            }, 5L);
        }
    }
}
