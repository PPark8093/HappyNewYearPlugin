package kr.pak.reality.time;

import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RealTimeCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (s.equalsIgnoreCase("RealTime")) {
            if (commandSender.isOp() || commandSender instanceof ConsoleCommandSender) {
                if (strings[0].isEmpty()) {
                    commandSender.sendMessage(ChatColor.RED + "Strings[0] 값을 입력하시오");
                }
                if (strings[0].equalsIgnoreCase("Get")) {
                    Date now = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.(E) HH:mm:ss", Locale.KOREAN);
                    long times = (long) (now.getHours() * 3600 + now.getMinutes() * 60 + now.getSeconds());
                    commandSender.sendMessage(dateFormat.format(now) + " | 설정된 시간:" + ((times * 0.2777777777777778) - 7600));
                    return true;
                } else {
                    commandSender.sendMessage(ChatColor.RED + "잘못된 명령어입니다!");
                }
            } else {
                commandSender.sendMessage(ChatColor.RED + "관리자가 아닙니다!");
            }
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (s.equalsIgnoreCase("RealTime")) {
            List<String> completes = new ArrayList<>();
            switch (strings.length) {
                case 1 -> {
                    completes.add("get");
                    completes.add("test");
                }
            }

            return completes;
        }

        return null;
    }
}
