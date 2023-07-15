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
import java.util.HashMap;
import java.util.List;

public class ProtectionItemManager {
    private final TycoonMain pl;

    public HashMap<Integer, Material> protectionTiers = new HashMap<>();

    public CustomConfig protectionConfig;
    public ProtectionItemManager(TycoonMain pl) {
        this.pl = pl;
        protectionConfig = new CustomConfig("configs","protections",pl);
        loadData();
    }


    private String name;
    private List<String> lore;


    public ItemStack getProtection(int tier,int amount){
        if (protectionTiers.containsKey(tier)){
            ItemStack i = new ItemStack(protectionTiers.get(tier),amount);
            ItemMeta im = i.getItemMeta();
            im.setDisplayName(Utils.asColor(name.replaceAll("%tier%",tier+"")));
            List<String> il = new ArrayList<>();
            lore.forEach(s->{
                il.add(Utils.asColor(s.replaceAll("%tier%",tier+"")));
            });
            im.setLore(il);
            im.getPersistentDataContainer().set(pl.protectionKey, PersistentDataType.INTEGER,tier);
            i.setItemMeta(im);

            return i;
        }else{
            return null;
        }
    }






    private void loadData(){
        FileConfiguration cfg = protectionConfig.getConfig();
        if (cfg.contains("ProtectionConfig")){
            for (String tierS : cfg.getConfigurationSection("ProtectionConfig.tiers").getKeys(false)){
                int tier = 0;
                try{
                    tier = Integer.parseInt(tierS);
                }catch (Exception ex){
                    Bukkit.getConsoleSender().sendMessage("&c[Tycoon] Wrong protection tier number found, can't translate "+tierS);
                }


                Material type = null;
                try{
                    type = Material.valueOf(cfg.getString("ProtectionConfig.tiers."+tierS));
                }catch (Exception ex){
                    Bukkit.getConsoleSender().sendMessage("&c[Tycoon] Wrong protection type found, can't translate "+cfg.getString("ProtectionConfig.tiers."+tierS));
                }
                protectionTiers.put(tier,type);
            }
            name = cfg.getString("ProtectionItem.name");
            lore = cfg.getStringList("ProtectionItem.lore");
        }else{
            loadDefData();
            loadData();
        }


    }
    private void loadDefData(){
        FileConfiguration cfg = protectionConfig.getConfig();
        cfg.set("ProtectionItem.name","&aTier %tier% &7Protection");
        cfg.set("ProtectionItem.lore", Arrays.asList("&7This protection will require a TNT of at least tier &a%tier%"));


        cfg.set("ProtectionConfig.tiers.1","STONE");
        cfg.set("ProtectionConfig.tiers.2","STONE_BRICKS");
        cfg.set("ProtectionConfig.tiers.3","SMOOTH_STONE");

        protectionConfig.save();
    }





}
