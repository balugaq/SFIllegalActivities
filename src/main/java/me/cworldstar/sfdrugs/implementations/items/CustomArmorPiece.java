package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import me.cworldstar.sfdrugs.SFDrugs;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class CustomArmorPiece extends SlimefunArmorPiece {
    private final SFDrugs plugin;

    public CustomArmorPiece(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,
                            PotionEffect[] effects) {
        super(itemGroup, item, recipeType, recipe, effects);
        // TODO Auto-generated constructor stub
        this.plugin = plugin;
    }
}
