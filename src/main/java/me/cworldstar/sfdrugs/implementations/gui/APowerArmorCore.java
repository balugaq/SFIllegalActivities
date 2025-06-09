package me.cworldstar.sfdrugs.implementations.gui;

import me.cworldstar.sfdrugs.implementations.powerarmor.PowerArmor;
import me.cworldstar.sfdrugs.implementations.powerarmor.PowerArmorCore;
import me.cworldstar.sfdrugs.implementations.powerarmor.PowerArmorCoreUpgrade;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class APowerArmorCore implements Listener {

    private final ArrayList<PowerArmorCoreUpgrade> Upgrades;
    private final PowerArmorCore Core;
    private final PowerArmor ArmorPiece;

    public APowerArmorCore(PowerArmor ArmorPiece) {
        this.ArmorPiece = ArmorPiece;
        this.Core = PowerArmor.getCore(ArmorPiece);
        this.Upgrades = PowerArmor.getUpgrades(ArmorPiece);
    }

}
