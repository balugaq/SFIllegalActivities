package me.cworldstar.sfdrugs.implementations.generators.supermatter;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;

public class SupermatterCrystal implements EnergyNetProvider {

    @Override
    public int getCapacity() {
        // TODO Auto-generated method stub
        return 20000;
    }

    @Override
    public String getId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getGeneratedOutput(Location l, Config data) {
        // TODO Auto-generated method stub
        if (data.getValue("sm output") != null) {
            return (int) data.getValue("sm output");
        }

        return 1000;
    }

}
