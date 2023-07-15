package ro.Stellrow.MCTycoon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ro.Stellrow.MCTycoon.TycoonMain;
import ro.Stellrow.MCTycoon.internalutils.InventoryUtils;
import ro.Stellrow.MCTycoon.internalutils.Utils;

public class TycoonCommand implements CommandExecutor {
    private final TycoonMain pl;

    public TycoonCommand(TycoonMain pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (args.length==3&&args[0].equalsIgnoreCase("getbanknote")){
            if (sender.hasPermission("tycoon.admin") && sender instanceof Player){
                Player p = (Player) sender;
                int currency = 0;
                int amount = 0;

                try{
                    currency = Integer.parseInt(args[1]);
                }catch (Exception ex){
                    p.sendMessage(Utils.asColor("&cWrong currency number!"));
                    return true;
                }

                try{
                    amount = Integer.parseInt(args[2]);
                }catch (Exception ex){
                    p.sendMessage(Utils.asColor("&cWrong amount number!"));
                    return true;
                }


                ItemStack bankNote = pl.bankNoteManager.getBankNote(currency,amount);
                InventoryUtils.addItem(p,bankNote);
                return true;
            }
        }

        if (args.length==3&&args[0].equalsIgnoreCase("getgenerator")){
            if (sender.hasPermission("tycoon.admin") && sender instanceof Player){
                Player p = (Player) sender;
                int tier = 0;
                int amount = 0;

                try{
                    tier = Integer.parseInt(args[1]);
                }catch (Exception ex){
                    p.sendMessage(Utils.asColor("&cWrong tier number!"));
                    return true;
                }

                try{
                    amount = Integer.parseInt(args[2]);
                }catch (Exception ex){
                    p.sendMessage(Utils.asColor("&cWrong amount number!"));
                    return true;
                }


                ItemStack generator = pl.generatorItemManager.getGenerator(tier,amount);
                InventoryUtils.addItem(p,generator);
                return true;
            }
        }

        if (args.length==3&&args[0].equalsIgnoreCase("getprotection")){
            if (sender.hasPermission("tycoon.admin") && sender instanceof Player){
                Player p = (Player) sender;
                int tier = 0;
                int amount = 0;

                try{
                    tier = Integer.parseInt(args[1]);
                }catch (Exception ex){
                    p.sendMessage(Utils.asColor("&cWrong tier number!"));
                    return true;
                }

                try{
                    amount = Integer.parseInt(args[2]);
                }catch (Exception ex){
                    p.sendMessage(Utils.asColor("&cWrong amount number!"));
                    return true;
                }


                ItemStack protection = pl.protectionItemManager.getProtection(tier,amount);
                InventoryUtils.addItem(p,protection);
                return true;
            }
        }


        return true;
    }
}
