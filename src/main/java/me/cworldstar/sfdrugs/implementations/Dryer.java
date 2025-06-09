package me.cworldstar.sfdrugs.implementations;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.sfdrugs.utils.Items;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Dryer extends AContainer implements RecipeDisplayItem, NotHopperable {
    @Override
    public @NotNull List<ItemStack> getDisplayRecipes() {
        return super.getDisplayRecipes();
    }

    public Dryer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        // TODO Auto-generated constructor stub
        this.setCapacity(480);
        this.setEnergyConsumption(120);
        this.setProcessingSpeed(20);
    }

    @Override
    public void registerDefaultRecipes() {
        addRecipe(20, new ItemStack[]{new ItemStack(Material.PAPER), Items.TRAY}, new ItemStack[]{new CustomItemStack(Items.METH, 8), Items.TRAY});
        addRecipe(20, new ItemStack[]{new ItemStack(Material.STICK, 64), Items.TRAY}, new ItemStack[]{Items.SNAIDS});
        addRecipe(20, new ItemStack[]{new ItemStack(Material.OAK_SAPLING, 64), Items.TRAY}, new ItemStack[]{Items.DRIED_PLANT});
        addRecipe(20, new ItemStack[]{stack(Items.DRIED_PLANT, 64), Items.TRAY}, new ItemStack[]{Items.CYANIDE});
        addRecipe(20, new ItemStack[]{Items.METH_COMPOUND, Items.TRAY}, new ItemStack[]{Items.METH});
        addRecipe(20, new ItemStack[]{stack(Items.IRRADIATED_SIRTHIUM_RAW, 16), Items.TRAY}, new ItemStack[]{Items.IRRADIATED_SIRTHIUM_PROCESSED});
    }

    public static ItemStack stack(ItemStack item, int amount) {
        return new CustomItemStack(item, amount);
    }

    @Override
    public String getMachineIdentifier() {
        return getId();
    }

    private void addRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        registerRecipe(seconds, input, output);
    }

    @Override
    public ItemStack getProgressBar() {

        return new ItemStack(Material.CAMPFIRE);
    }
}
