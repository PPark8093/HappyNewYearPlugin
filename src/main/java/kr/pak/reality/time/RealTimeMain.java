package kr.pak.reality.time;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

public class RealTimeMain {

    public void SetTime(Logger logger) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("HappyNewYear");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.(E) HH:mm:ss", Locale.KOREAN);
            // String date = dateFormat.format(now);
            // logger.info(date);

            // logger.info("H: " + hours + " M: " + minutes + " S: " + seconds);
            // logger.info("Total Seconds: " + (hours * 3600 + minutes * 60 + seconds));
            long times = (long) (now.getHours() * 3600 + now.getMinutes() * 60 + now.getSeconds()); // 지금이 몇초째인지

            // 월드 시간 정하기
            Bukkit.getWorld("world").setTime((long) ((times * 0.2777777777777778) - 7600));

            if (now.getMinutes() == 0 && now.getSeconds() == 0) {
                Bukkit.broadcastMessage(ChatColor.GRAY.toString() + now.getHours() + "시 정각입니다!");
            }
            //logger.info("설정된 시간: " + ((times * 0.2777777777777778) - 7600));

            // Bukkit.getWorld("world").setTime((long) (Bukkit.getWorld("world").getTime() + 0.2777777777777778)); // 20틱 = 1초

        },0, 20L);
        // l: 실행 전 서버 틱 지연, l1: 작업 중간의 서버 틱 시간 20L = 1초
    }
}
