package me.cworldstar.sfdrugs.dimensions.biomes;

import me.cworldstar.sfdrugs.dimensions.biomes.BuildingConstraint.ConstraintType;
import org.bukkit.Chunk;

public class VectorConstraint extends Constraint {

    public VectorConstraint(ConstraintType t, Object x) {
        super(t, x);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean validate(Chunk chunk) {
        // TODO Auto-generated method stub
        return true;
    }


}
