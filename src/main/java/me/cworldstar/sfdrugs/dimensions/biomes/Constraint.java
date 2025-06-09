package me.cworldstar.sfdrugs.dimensions.biomes;

import me.cworldstar.sfdrugs.dimensions.biomes.BuildingConstraint.ConstraintType;
import org.bukkit.Chunk;

public abstract class Constraint {

    private ConstraintType Type;
    private Object OtherShit;

    public Constraint(ConstraintType t, Object x) {
        // TODO Auto-generated constructor stub
        this.Type = t;
        this.OtherShit = x;
    }

    public Constraint() {
        // TODO Auto-generated constructor stub
    }

    public ConstraintType getType() {
        return this.Type;
    }

    public Object getOtherShit() {
        return this.OtherShit;
    }

    public abstract boolean validate(Chunk chunk);

}
