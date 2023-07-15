package ro.Stellrow.MCTycoon.managers.statics;

import ro.Stellrow.MCTycoon.TycoonMain;
import ro.Stellrow.MCTycoon.components.ProtectionBlock;

import java.util.HashSet;
import java.util.Set;

public class ProtectionManager {
    private final TycoonMain pl;
    public ProtectionManager(TycoonMain pl) {
        this.pl = pl;
    }


    private Set<ProtectionBlock> protectionBlocks = new HashSet<>();


}
