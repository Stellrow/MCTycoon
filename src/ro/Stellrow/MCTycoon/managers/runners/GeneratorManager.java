package ro.Stellrow.MCTycoon.managers.runners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import ro.Stellrow.MCTycoon.TycoonMain;
import ro.Stellrow.MCTycoon.components.Generator;

import java.util.HashSet;
import java.util.Set;

public class GeneratorManager {
    private final TycoonMain pl;

    public GeneratorManager(TycoonMain pl) {
        this.pl = pl;
        run();
    }


    public Set<Generator> generators = new HashSet<>();

    public void addGenerator(Block block,int tier){
        generators.add(new Generator(pl,block, tier));
    }
    public void removeGenerator(){

    }



    private void run(){
        Bukkit.getScheduler().runTaskTimer(pl,()->{
            generators.forEach(Generator::spawnDrop);
        },0,5*20);
    }


}
