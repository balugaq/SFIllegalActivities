package me.cworldstar.sfdrugs.dimensions.biomes;

import me.cworldstar.sfdrugs.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;

import java.util.List;

public class CorporateDimensionProvider extends BiomeProvider {
    private final World world;
    private Location XYZ;

    public CorporateDimensionProvider(World w) {
        this.world = w;
        this.setXYZ(w, RandomUtils.getRandom().nextDouble() * 20000, RandomUtils.getRandom().nextDouble() * 20000, RandomUtils.getRandom().nextDouble() * 20000);
    }

    private void setXYZ(World w, double x, double y, double z) {
        this.XYZ = new Location(w, x, y, z);
    }

    @Override
    public Biome getBiome(WorldInfo worldInfo, int x, int y, int z) {
        if (new Location(this.world, x, y, z).getChunk().equals(this.XYZ.getChunk())) {
            return Biome.WOODED_HILLS;
        }
        return Biome.WOODED_MOUNTAINS;
    }

    @Override
    public List<Biome> getBiomes(WorldInfo worldInfo) {
        // TODO Auto-generated method stub
        return null;
    }

}
