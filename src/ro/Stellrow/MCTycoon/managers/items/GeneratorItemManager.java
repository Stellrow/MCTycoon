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

public class GeneratorItemManager {
    private final TycoonMain pl;



    public CustomConfig generatorConfig;
    public GeneratorItemManager(TycoonMain pl) {
        this.pl = pl;
        generatorConfig = new CustomConfig("configs","generators",pl);
        loadData();
    }

    private Material type;
    private String name;
    private List<String> lore;




    public ItemStack getGenerator(int tier,int amount){
        ItemStack i = new ItemStack(type,amount);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(Utils.asColor(name.replaceAll("%tier%",tier+"")));
        List<String> il = new ArrayList<>();
        lore.forEach(s->{
            il.add(Utils.asColor(s.replaceAll("%tier%",tier+"")));
        });
        im.setLore(il);
        im.getPersistentDataContainer().set(pl.generatorKey, PersistentDataType.INTEGER,tier);
        i.setItemMeta(im);
        return i;
    }




    private void loadData(){
        FileConfiguration cfg = generatorConfig.getConfig();
        if (cfg.contains("GeneratorItem")){
            try{
                type = Material.valueOf(generatorConfig.getConfig().getString("GeneratorItem.type"));
            }catch (Exception ex){
                Bukkit.getConsoleSender().sendMessage(Utils.asColor("&c[Tycoon] Wrong money type for bank note no such type, defaulting to GLASS"));
                type = Material.GLASS;
            }
            name = generatorConfig.getString("GeneratorItem.name");
            lore = generatorConfig.getConfig().getStringList("GeneratorItem.lore");
        }else{
            loadDefData();
            loadData();
        }
    }

    private void loadDefData(){
        FileConfiguration cfg = generatorConfig.getConfig();
        cfg.set("GeneratorItem.type","GLASS");
        cfg.set("GeneratorItem.name","&aTier %tier% &7Generator");
        cfg.set("GeneratorItem.lore", Arrays.asList("&7Place me down to start generating money!"));
        generatorConfig.save();
    }
}
