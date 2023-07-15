package ro.Stellrow.MCTycoon.internalutils;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class EconomyHandler {
    private final JavaPlugin pl;
    public boolean hasEco;
    public static Economy economy = null;


    public EconomyHandler(JavaPlugin pl) {
        this.pl = pl;
    }
    public void init(){
        findVault();
    }

    private void findVault() {
        if(pl.getServer().getPluginManager().getPlugin("Vault")!=null&&pl.getServer().getPluginManager().isPluginEnabled("Vault")) {
            setupEconomy();
            return;
        }
        pl.getServer().getConsoleSender().sendMessage("["+pl.getName()+"]"+ ChatColor.RED+" Vault wasnt found! Using money feature is disabled!");
        return;
    }
    private boolean setupEconomy()
    {
        RegisteredServiceProvider<Economy> economyProvider = pl.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
            hasEco=true;
        }
        return (economy != null);
    }
}