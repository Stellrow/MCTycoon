package ro.Stellrow.MCTycoon.components;

import org.bukkit.block.Block;
import ro.Stellrow.MCTycoon.TycoonMain;

public class Generator {
    private final TycoonMain main;
    public final Block block;

    public int tier;

    public Generator(TycoonMain main, Block block, int tier) {
        this.main = main;
        this.block = block;
        this.tier = tier;
    }


    public void spawnDrop(){
        if (block!=null){
            var items = main.rewardManager.GetRewardForGenerator(this);
            items.forEach(itemStack -> {
                block.getWorld().dropItemNaturally(block.getLocation().add(0,1,0),itemStack);
            });
        }
    }

    public void upgradeTier(){
        //Check conditions
        tier++;
    }

}
