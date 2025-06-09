package me.cworldstar.sfdrugs.implementations.generators.supermatter;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.cworldstar.sfdrugs.utils.Constants;
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
        if (data.getValue(Constants.sm_output) != null) {
            return (int) data.getValue(Constants.sm_output);
        }

        return 1000;
    }

}
