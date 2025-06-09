package me.cworldstar.sfdrugs.dimensions;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.dimensions.biomes.CorporateDimensionProvider;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.List;

public class CorporateDimension {
    public CorporateDimension(SFDrugs plugin) {
        this.init(plugin);
    }

    public void init(SFDrugs plugin) {
        List<World> w = plugin.getServer().getWorlds();
        if (plugin.getServer().getWorld(Constants.corporate_dimension) == null) {
            WorldCreator w2 = new WorldCreator(Constants.corporate_dimension);
            w2.generateStructures(false);
            w2.biomeProvider(new CorporateDimensionProvider(w2.createWorld()));
        }
    }
}
