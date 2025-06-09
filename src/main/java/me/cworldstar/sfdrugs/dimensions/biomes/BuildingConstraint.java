package me.cworldstar.sfdrugs.dimensions.biomes;

import com.sk89q.worldedit.math.Vector3;
import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.Objects;

public class BuildingConstraint extends Constraint {

    public final ArrayList<Constraint> constraints = new ArrayList<>();

    public BuildingConstraint() {
        super();
    }

    @Override
    public boolean validate(Chunk chunk) {
        for (Constraint c : this.constraints) {
            if (c.validate(chunk)) {
                return true;
            }
        }
        return false;
    }

    public void addConstraint(ConstraintType T, Object x) {
        if (Objects.requireNonNull(T) == ConstraintType.Position) {
            if (!(x instanceof Vector3)) {
                throw new Error("Constraint object X invalid type for " + T);
            } else {
                VectorConstraint VectorConstraint = new VectorConstraint(T, x);
                constraints.add(VectorConstraint);
            }
        }

    }

    public enum ConstraintType {

        Position,
        Entity,
        Time

    }

}
