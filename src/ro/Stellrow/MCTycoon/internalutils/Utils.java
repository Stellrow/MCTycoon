package ro.Stellrow.MCTycoon.internalutils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String asColor(String toTranslate){
        return ChatColor.translateAlternateColorCodes('&',toTranslate);
    }
    public static List<String> listAsColor(List<String> toTranslate){
        List<String> toReturn = new ArrayList<>();
        for(String s : toTranslate){
            toReturn.add(asColor(s));
        }
        return toReturn;
    }
    public static void hexTranslate(String string) {
        Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}}");
        Matcher match = HEX_PATTERN.matcher(string);
        while (match.find()) {
            String color = string.substring(match.start(), match.end());
            string = string.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            match = HEX_PATTERN.matcher(string);
        }
    }

}
