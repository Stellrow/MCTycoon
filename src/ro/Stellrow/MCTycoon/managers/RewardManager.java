package ro.Stellrow.MCTycoon.managers;

import org.bukkit.inventory.ItemStack;
import ro.Stellrow.MCTycoon.TycoonMain;
import ro.Stellrow.MCTycoon.components.Generator;

import java.util.ArrayList;
import java.util.List;

public class RewardManager {
    private final TycoonMain pl;

    public RewardManager(TycoonMain main) {
        this.pl = main;
    }

    public List<ItemStack> GetRewardForGenerator(Generator generator){
        List<ItemStack> money = new ArrayList<>();
        money.add(pl.bankNoteManager.getBankNote(5,1));
        return money;
    }


}
