package ro.Stellrow.MCTycoon.internalutils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {


   /*
  pot:
    type: FLOWER_POT
    name: '&7Mystic Pot'
    lore:
      - '&7A magical flower pot that'
      - '&7allows you to seed a unique plant'
    */

    public static ItemStack getItemFromConfigSection(ConfigurationSection section){
        ItemStack item = new ItemStack(Material.valueOf(section.getString("type")));
        ItemMeta im = item.getItemMeta();
        if (section.contains("name")) {
            im.setDisplayName(Utils.asColor(section.getString("name")));
        }
        if (section.contains("lore")) {
            im.setLore(Utils.listAsColor(section.getStringList("lore")));
        }
        item.setItemMeta(im);
        return item;
    }
    public static void setItemName(ItemStack affected,String toSet){
        ItemMeta im = affected.getItemMeta();
        im.setDisplayName(Utils.asColor(toSet));
        affected.setItemMeta(im);
    }
    public static void addLoreLine(ItemStack affected, String toAdd){
        ItemMeta im = affected.getItemMeta();
        if(im.hasLore()){
            List<String> lore = im.getLore();
            lore.add(Utils.asColor(toAdd));
            im.setLore(lore);
            affected.setItemMeta(im);
            return;
        }
        List<String> lore = new ArrayList<>();
        lore.add(toAdd);
        im.setLore(lore);
        affected.setItemMeta(im);
    }
    public static void addStringPDC(ItemStack itemStack,NamespacedKey nmk,String value){
        ItemMeta im = itemStack.getItemMeta();
        im.getPersistentDataContainer().set(nmk,PersistentDataType.STRING,value);
        itemStack.setItemMeta(im);
    }
    public static void addIntegerPDC(ItemStack itemStack,NamespacedKey nmk,int value){
        ItemMeta im = itemStack.getItemMeta();
        im.getPersistentDataContainer().set(nmk,PersistentDataType.INTEGER,value);
        itemStack.setItemMeta(im);
    }
    public static boolean hasPDC(ItemMeta im, NamespacedKey nmk, PersistentDataType type){
        return im.getPersistentDataContainer().has(nmk,type);
    }
    public static boolean hasPDC(ItemStack im, NamespacedKey nmk, PersistentDataType type){
        if (im.hasItemMeta()&&im.getItemMeta().getPersistentDataContainer().has(nmk,type)){
            return true;
        }
        return false;
    }
}
