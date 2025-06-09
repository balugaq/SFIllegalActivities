package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import org.bukkit.Material;

public class Blueprint {
    private final String blueprintName;

    public Blueprint(String BlueprintName) {
        this.blueprintName = BlueprintName;
    }

    public SlimefunItemStack getBlueprint() {
        return new SlimefunItemStack("SFDRUGS_".concat(this.blueprintName.replace(' ', '_').toUpperCase()).concat("_BLUEPRINT"), Material.FILLED_MAP, "&7".concat(this.blueprintName).concat(" Blueprint"));
    }

}
