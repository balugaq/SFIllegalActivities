package me.cworldstar.sfdrugs.implementations.redstone;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class ARedstoneBlock extends SlimefunItem implements Listener {

    public ARedstoneBlock(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {


        super(itemGroup, item, recipeType, recipe);
        // TODO Auto-generated constructor stub
    }
}
