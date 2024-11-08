package io.tofpu.speedbridge2.util;

import com.sk89q.worldedit.Vector;
import org.bukkit.Location;

public class LocationUtil {
    public static Vector asVector(Location location) {
        return new Vector(location.getX(), location.getY(), location.getZ());
    }
}
