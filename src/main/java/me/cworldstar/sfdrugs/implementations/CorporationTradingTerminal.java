package me.cworldstar.sfdrugs.implementations;


import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.infinitylib.machines.CraftingBlock;
import io.github.mooy1.infinitylib.machines.MachineLayout;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.cworldstar.sfdrugs.utils.Items;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.jetbrains.annotations.NotNull;

public class CorporationTradingTerminal extends CraftingBlock implements Radioactive,EnergyNetComponent {
	public CorporationTradingTerminal(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
			ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
		addRecipesFrom(Items.CORPORATION_TRADE);
		MachineLayout L = new MachineLayout();
		L.inputSlots(new int[] {
			12,13,14,
			21,22,23,
			30,31,32
		});
		L.outputSlots(new int[] {
			48,49,50
		});
		L.inputBorder(new int[] {
			2,3,4,5,6,
			11,15,
			20,24,
			29,33,
			38,39,41,42
		});
		L.outputBorder(new int[] {
			47,51	
		});
		L.statusSlot(40);
		layout(L);
		// TODO Auto-generated constructor stub
	}
	@Override 
	public void setup(@NotNull BlockMenuPreset preset) {
		super.setup(preset);
	}
    @Override
    public void craft(Block b, BlockMenu menu, Player p) {
    	int charge = getCharge(menu.getLocation());
    	if(charge >= 1280) {
    		super.craft(b, menu, p);
    	}
    	
    }
    @Override 
    public void onSuccessfulCraft(BlockMenu menu, ItemStack output) {
    	this.removeCharge(menu.getLocation(), 1280);
    	menu.getLocation().getWorld().playEffect(menu.getLocation(), Effect.DRAGON_BREATH, 10);
    	menu.getLocation().getWorld().playSound(menu.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 0.5F, 0.5F);
    	menu.getLocation().getWorld().createExplosion(menu.getLocation(), 1L, false, false);
    }
	
    @Override
	public Radioactivity getRadioactivity() {
		// TODO Auto-generated method stub
		return Radioactivity.MODERATE;
	}
	@Override
	public EnergyNetComponentType getEnergyComponentType() {
		// TODO Auto-generated method stub
		return EnergyNetComponentType.CONSUMER;
	}
	@Override
	public int getCapacity() {
		// TODO Auto-generated method stub
		return (1280 * 4);
	}

}
