package ro.Stellrow.MCTycoon;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import ro.Stellrow.MCTycoon.commands.TycoonCommand;
import ro.Stellrow.MCTycoon.events.GeneratorEvents;
import ro.Stellrow.MCTycoon.managers.items.BankNoteManager;
import ro.Stellrow.MCTycoon.managers.items.GeneratorItemManager;
import ro.Stellrow.MCTycoon.managers.RewardManager;
import ro.Stellrow.MCTycoon.managers.items.ProtectionItemManager;
import ro.Stellrow.MCTycoon.managers.runners.GeneratorManager;
import ro.Stellrow.MCTycoon.managers.statics.ProtectionManager;

public class TycoonMain extends JavaPlugin {


    public NamespacedKey bankNoteKey = new NamespacedKey(this,"tycoon.bank-note");
    public NamespacedKey generatorKey = new NamespacedKey(this,"tycoon.generator");
    public NamespacedKey protectionKey = new NamespacedKey(this,"tycoon.protection");


    public RewardManager rewardManager;
    public BankNoteManager bankNoteManager;
    public GeneratorItemManager generatorItemManager;
    public ProtectionItemManager protectionItemManager;



    public GeneratorManager generatorManager;
    public ProtectionManager protectionManager;

    @Override
    public void onEnable() {
        rewardManager = new RewardManager(this);
        bankNoteManager = new BankNoteManager(this);
        generatorItemManager = new GeneratorItemManager(this);
        protectionItemManager = new ProtectionItemManager(this);


        generatorManager = new GeneratorManager(this);
        protectionManager = new ProtectionManager(this);
        getCommand("tycoon").setExecutor(new TycoonCommand(this));


        getServer().getPluginManager().registerEvents(new GeneratorEvents(this),this);
    }

    @Override
    public void onDisable() {

    }
}
