package me.cworldstar.sfdrugs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPluginLoader;

import io.github.mooy1.infinitylib.core.AbstractAddon;
import io.github.mooy1.infinitylib.core.AddonConfig;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.cworldstar.sfdrugs.dimensions.CorporateDimension;
import me.cworldstar.sfdrugs.dimensions.biomes.BuildingConstraint;
import me.cworldstar.sfdrugs.dimensions.biomes.CorporateDimensionBuildings;
import me.cworldstar.sfdrugs.events.ArmorUpgradeListeners;
import me.cworldstar.sfdrugs.events.CorporationTraderEvent;
import me.cworldstar.sfdrugs.events.CustomMobDeathEvent;
import me.cworldstar.sfdrugs.events.DrugSuitDamaged;
import me.cworldstar.sfdrugs.events.DrugSuitWearerDamaged;
import me.cworldstar.sfdrugs.events.GangMemberSpawnEvent;
import me.cworldstar.sfdrugs.events.LaserProjectileHit;
import me.cworldstar.sfdrugs.events.MysteriousTraderEvent;
import me.cworldstar.sfdrugs.events.PlayerAddedEvent;
import me.cworldstar.sfdrugs.events.PowerArmorListener;
import me.cworldstar.sfdrugs.events.RobotArmorDamaged;
import me.cworldstar.sfdrugs.events.RobotArmorPieceEquipped;
import me.cworldstar.sfdrugs.events.SFHookerEvent;
import me.cworldstar.sfdrugs.events.UnstableObjectEvent;
import me.cworldstar.sfdrugs.implementations.commands.GiveUpgrade;
import me.cworldstar.sfdrugs.implementations.commands.RefreshTraders;
import me.cworldstar.sfdrugs.implementations.commands.TestCorporationEnemy;
import me.cworldstar.sfdrugs.implementations.events.ArmorListener;
import me.cworldstar.sfdrugs.implementations.traders.CorporationTrader;
import me.cworldstar.sfdrugs.utils.Items;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Trading;

public class SFDrugs extends AbstractAddon implements SlimefunAddon {
    public SFDrugs(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file,
                "Sniperkaos", "SFIllegalActivities", "master", "auto-update");
	}

    
    public SFDrugs() {
    	super("Sniperkaos","SFIllegalActivities","master","auto-update");
    }    
    
    @SuppressWarnings("unused")
	@Override
	public void enable() {
    	
    	
    	RandomUtils ThisIsSoStupid = new RandomUtils();
		AddonConfig cfg = this.getConfig();
		cfg.addDefault("naniteSynthesizer.maxX", 5000);
		cfg.addDefault("naniteSynthesizer.maxZ", 5000);
		cfg.save();

		if (Bukkit.getPluginManager().isPluginEnabled("WorldEdit") || Bukkit.getPluginManager().isPluginEnabled("FastAsyncWorldEdit")) {
			CorporateDimensionBuildings BuildingEvent = new CorporateDimensionBuildings();

			File schemFiles = new File(this.getDataFolder(), "schematics");
			if (schemFiles.exists() && schemFiles.listFiles().length > 0) {
				for (File f : schemFiles.listFiles()) {
					BuildingEvent.RegisterBuilding(f.getName(), f, new BuildingConstraint());
				}
			} else {

				schemFiles.mkdir();
				this.saveResource("corporate_building.schem", false);

				for (File f : schemFiles.listFiles()) {
					BuildingEvent.RegisterBuilding(f.getName(), f, new BuildingConstraint());
				}

			}
		}
    	new CorporateDimension(this);
    	Items ItemRegistry = new Items(this);
		getServer().getPluginManager().registerEvents(new ArmorListener(new ArrayList<String>()), this);
		CustomMobDeathEvent DeathEvent = new CustomMobDeathEvent(this);
    	DrugSuitDamaged DamageEvent = new DrugSuitDamaged(this);
    	DrugSuitWearerDamaged DamageEvent2 = new DrugSuitWearerDamaged(this);
    	PowerArmorListener L = new PowerArmorListener(this);
    	ItemRegistry.register();
    	Trading TradingRegistry = new Trading(this);
    	TradingRegistry.register();
    	
    	

    	ArmorUpgradeListeners ShakingMyHead = new ArmorUpgradeListeners(this);
    	/**
    	 * 
    	 * Implementation for UnstableObjects.
    	 * 
    	 */
    	
    	
    	PlayerAddedEvent WhoEvenReadsThese = new PlayerAddedEvent(this);
    	CorporationTrader CorporateTraderRegister = new CorporationTrader();
    	UnstableObjectEvent IDont = new UnstableObjectEvent(this);
    	SFHookerEvent HookerEvent = new SFHookerEvent(this,TradingRegistry);
    	CorporationTraderEvent TraderEvent = new CorporationTraderEvent(this,TradingRegistry);
    	MysteriousTraderEvent TraderEvent2 = new MysteriousTraderEvent(this,TradingRegistry);
    	RobotArmorDamaged RobotArmorEvent = new RobotArmorDamaged(this);
    	RobotArmorPieceEquipped RobotArmorPieceEquipped = new RobotArmorPieceEquipped(this);
    	LaserProjectileHit LaserProjectileHitEvent = new LaserProjectileHit(this);
    	GangMemberSpawnEvent GangMemberSpawn = new GangMemberSpawnEvent(this);
    	TestCorporationEnemy Command = new TestCorporationEnemy(this);
    	this.getCommand("test").setExecutor(Command);
    	GiveUpgrade Command2 = new GiveUpgrade(this);
    	this.getCommand("give_upgrade").setExecutor(Command2);
    	RefreshTraders RefreshTraderCommand = new RefreshTraders(this);
    	this.getCommand("refresh_trader").setExecutor(RefreshTraderCommand);
    	
    	Logger x = getLogger();
    	x.log(Level.INFO, "============================================");
    	x.log(Level.INFO, "====                                     ===");
    	x.log(Level.INFO, "====         SF DRUGS ENABLED            ===");
    	x.log(Level.INFO, "====             v ".concat(this.getPluginVersion()).concat("                 ==="));
    	x.log(Level.INFO, "====         by China Worldstar          ===");
    	x.log(Level.INFO, "====                                     ===");
    	x.log(Level.INFO, "============================================");
    }
    @Override
    public void disable() {
    	
    	
    	
    }

}
