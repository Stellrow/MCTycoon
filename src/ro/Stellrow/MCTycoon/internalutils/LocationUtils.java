package ro.Stellrow.MCTycoon.internalutils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {
    public static String locationToString(Location location){
        return location.getWorld().getName()+"|"+location.getBlockX()+"|"+location.getBlockY()+"|"+location.getBlockZ();
    }
    public static String locationPreciseToString(Location location){
        return location.getWorld().getName()+"|"+location.getX()+"|"+location.getY()+"|"+ location.getZ()+"|"+location.getYaw()+"|"+location.getPitch();
    }
    public static Location stringToLocation(String toTranslate){
        String[] raw = toTranslate.split("\\|");
        return new Location(Bukkit.getWorld(raw[0]),Integer.parseInt(raw[1]),Integer.parseInt(raw[2]),Integer.parseInt(raw[3]));
    }
    public static Location stringToLocationPrecise(String toTranslate){
        String[] raw = toTranslate.split("\\|");
        return new Location(Bukkit.getWorld(raw[0]),Double.parseDouble(raw[1]),Double.parseDouble(raw[2]),Double.parseDouble(raw[3]),Float.parseFloat(raw[4]),Float.parseFloat(raw[5]));
    }
}
