package me.cworldstar.sfdrugs.implementations.multiblocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Map;

public abstract class AMultiBlock {

    private final Location middle;
    private final Map<Vector, Block> blockmap;
    private int passedChecks = 0;

    public AMultiBlock(Location Middle, Map<Vector, Block> BlockMap) {

        this.middle = Middle;
        this.blockmap = BlockMap;


    }

    public static Map<Vector, Material> Handle(Map<Vector, Material> map, Material b, int xOffset, int yOffset, int zOffset) {

        map.put(new Vector(xOffset, yOffset, zOffset), b);

        return map;
    }

    public static Map<Vector, Material> Layer(Map<Vector, Material> map, Material m) {

        map.put(new Vector(1, 0, 0), m);
        map.put(new Vector(0, 0, 1), m);
        map.put(new Vector(-1, 0, 0), m);
        map.put(new Vector(0, 0, -1), m);
        map.put(new Vector(-1, 0, -1), m);
        map.put(new Vector(1, 0, 1), m);
        map.put(new Vector(1, 0, -1), m);
        map.put(new Vector(-1, 0, 1), m);
        map.put(new Vector(0, 0, 0), m);

        return map;
    }

    public boolean ValidateStructure() {
        blockmap.forEach((Vector v, Block b) -> {
            if (this.middle.add(v).getBlock().equals(b)) {
                passedChecks++;
            }
        });
        if (passedChecks >= blockmap.size()) {
            passedChecks = 0;
            return true;
        }
        passedChecks = 0;
        return false;

    }

}
