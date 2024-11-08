package io.tofpu.speedbridge2.util;

import org.bukkit.ChatColor;

public class ColorUtil {
    public static String colorize(String content) {
        return ChatColor.translateAlternateColorCodes('&', content);
    }
}
