package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import org.bukkit.inventory.ItemStack;

public class IrradiatedItem extends SlimefunItem implements Radioactive {
    public final Radioactivity radioactivity;

    public IrradiatedItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, Radioactivity Radioactive) {
        super(itemGroup, item, recipeType, recipe);
        // TODO Auto-generated constructor stub
        this.radioactivity = Radioactive;
    }

    @Override
    public Radioactivity getRadioactivity() {
        // TODO Auto-generated method stub
        return this.radioactivity;
    }

}
