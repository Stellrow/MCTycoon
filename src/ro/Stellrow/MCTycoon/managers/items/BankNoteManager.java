package ro.Stellrow.MCTycoon.managers.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ro.Stellrow.MCTycoon.TycoonMain;
import ro.Stellrow.MCTycoon.internalutils.CustomConfig;
import ro.Stellrow.MCTycoon.internalutils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankNoteManager {
    private final TycoonMain main;
    //Store data about bank notes
    public CustomConfig notesConfig;

    public ItemStack noteItem;

    public BankNoteManager(TycoonMain main) {
        this.main = main;
        notesConfig = new CustomConfig("configs","bankNotes",main);
        loadData();
    }


    private Material type;
    private String name;
    private List<String> lore;


    private void loadData(){
        if (notesConfig.getConfig().contains("ItemConfig")){
            try{
                type = Material.valueOf(notesConfig.getConfig().getString("ItemConfig.type"));
            }catch (Exception ex){
                Bukkit.getConsoleSender().sendMessage(Utils.asColor("&c[Tycoon] Wrong money type for bank note no such type, defaulting to PAPER"));
                type = Material.PAPER;
            }
            name = notesConfig.getString("ItemConfig.name");
            lore = notesConfig.getConfig().getStringList("ItemConfig.lore");
        }else{
            loadDefItemConfig();
            loadData();
        }
    }

    public ItemStack getBankNote(int currency,int itemAmount){
        ItemStack itemStack = new ItemStack(type,itemAmount);
        ItemMeta im = itemStack.getItemMeta();
        im.setDisplayName(Utils.asColor(name.replaceAll("%value%",currency+"")));
        List<String> il = new ArrayList<>();
        lore.forEach(s->{
            il.add(Utils.asColor(s.replaceAll("%value%",currency+"")));
        });
        im.setLore(il);
        im.getPersistentDataContainer().set(main.bankNoteKey, PersistentDataType.INTEGER,currency);
        itemStack.setItemMeta(im);

        return itemStack;
    }

    private void loadDefItemConfig(){
        FileConfiguration cfg = notesConfig.getConfig();
        cfg.set("ItemConfig.type","PAPER");
        cfg.set("ItemConfig.name","&a%value%$ Bill");
        cfg.set("ItemConfig.lore", Arrays.asList("&7Sell me at the shop to gain &a%value%$"));
        notesConfig.save();
    }
}
