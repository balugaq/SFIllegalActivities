package me.cworldstar.sfdrugs.implementations.items;


import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import org.bukkit.inventory.ItemStack;

public class ElectricShears extends SimpleSlimefunItem<ToolUseHandler> implements Radioactive, Rechargeable {

    public ElectricShears(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        // TODO Auto-generated constructor stub
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        // TODO Auto-generated method stub
        return 1280;
    }

    @Override
    public Radioactivity getRadioactivity() {
        // TODO Auto-generated method stub
        return Radioactivity.LOW;
    }

    @Override
    public ToolUseHandler getItemHandler() {
        // TODO Auto-generated method stub
        return (e, tool, fortune, drops) -> {
            if (this.getItemCharge(tool) > 0) {
                if (!this.removeItemCharge(tool, 5)) {
                    e.setCancelled(true);
                }
            }
        };
    }


}
