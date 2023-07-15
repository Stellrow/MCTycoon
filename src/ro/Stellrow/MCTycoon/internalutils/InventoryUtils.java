package ro.Stellrow.MCTycoon.internalutils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class InventoryUtils {

    public static void addItem(Player player, ItemStack toAdd){
        if (player.getInventory().firstEmpty()==-1){
            player.sendMessage(Utils.asColor("&cAi primit un item dar nu ai avut destul spatiu,se arunca pe jos!"));
            player.getWorld().dropItemNaturally(player.getLocation(),toAdd);
            return;
        }
        player.getInventory().addItem(toAdd);
    }

    public static int getAmountForItem(Player p,Material material){
        int amount = 0;
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.getType()==material){
                        amount+=i.getAmount();
                }
            }
        }
        return amount;
    }

    public static int getAmountForItem(Player p, NamespacedKey key){
        int amount = 0;
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.hasItemMeta()){
                    if (i.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)){
                        amount+=i.getAmount();
                    }
                }
            }
        }
        return amount;
    }
    public static int getAmountForItem(Player p,Material type, NamespacedKey key){
        int amount = 0;
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.getType()==type){
                    if (i.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)){
                        amount+=i.getAmount();
                    }
                }
            }
        }
        return amount;
    }
    public static int getAmountForItem(Player p,Material type, NamespacedKey key,PersistentDataType dataType){
        int amount = 0;
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.getType()==type){
                    if (i.getItemMeta().getPersistentDataContainer().has(key, dataType)){
                        amount+=i.getAmount();
                    }
                }
            }
        }
        return amount;
    }

    public static void reduceAmount(Player p,int amount,Material type){
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.getType()==type){
                        if (amount>i.getAmount()){
                            amount-=i.getAmount();
                            i.setAmount(0);
                        }else{
                            i.setAmount(i.getAmount()-amount);
                            break;
                        }
                }
            }
        }
    }
    public static void reduceAmount(Player p,int amount,NamespacedKey key){
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.hasItemMeta()){
                    if (i.getItemMeta().getPersistentDataContainer().has(key,PersistentDataType.STRING)){
                        if (amount>i.getAmount()){
                            amount-=i.getAmount();
                            i.setAmount(0);
                        }else{
                            i.setAmount(i.getAmount()-amount);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void reduceAmount(Player p,int amount,Material type,NamespacedKey key){
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.getType()==type){
                    if (i.getItemMeta().getPersistentDataContainer().has(key,PersistentDataType.STRING)){
                        if (amount>i.getAmount()){
                            amount-=i.getAmount();
                            i.setAmount(0);
                        }else{
                            i.setAmount(i.getAmount()-amount);
                            break;
                        }
                    }
                }
            }
        }
    }
    public static void reduceAmount(Player p,int amount,Material type,NamespacedKey key,PersistentDataType dataType){
        for (ItemStack i : p.getInventory().getContents()){
            if (i!=null){
                if (i.getType()==type){
                    if (i.getItemMeta().getPersistentDataContainer().has(key,dataType)){
                        if (amount>i.getAmount()){
                            amount-=i.getAmount();
                            i.setAmount(0);
                        }else{
                            i.setAmount(i.getAmount()-amount);
                            break;
                        }
                    }
                }
            }
        }
    }

}
