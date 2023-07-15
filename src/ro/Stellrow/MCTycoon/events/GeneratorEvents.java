package ro.Stellrow.MCTycoon.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import ro.Stellrow.MCTycoon.TycoonMain;

public class GeneratorEvents implements Listener {
    private final TycoonMain pl;

    public GeneratorEvents(TycoonMain pl) {
        this.pl = pl;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if (event.isCancelled()){
            return;
        }


        if (event.getItemInHand().hasItemMeta()&&event.getItemInHand().getItemMeta().getPersistentDataContainer().has(pl.generatorKey, PersistentDataType.INTEGER)){
            ItemStack i = event.getItemInHand();
            ItemMeta im = i.getItemMeta();
            int tier = im.getPersistentDataContainer().get(pl.generatorKey,PersistentDataType.INTEGER);
            i.setAmount(i.getAmount()-1);
            pl.generatorManager.addGenerator(event.getBlockPlaced(),tier);

            event.getPlayer().sendMessage("Placed G");
        }
    }
}
