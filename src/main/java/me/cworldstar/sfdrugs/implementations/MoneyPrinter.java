package me.cworldstar.sfdrugs.implementations;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.cworldstar.sfdrugs.utils.Items;
import me.cworldstar.sfdrugs.utils.Texts;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class MoneyPrinter extends AContainer implements RecipeDisplayItem {
    public MoneyPrinter(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        // TODO Auto-generated constructor stub
        this.setCapacity(12800);
        this.setEnergyConsumption(1280);
        this.setProcessingSpeed(20);
        registerDefaultRecipes();
    }

    @Override
    public @NotNull List<ItemStack> getDisplayRecipes() {
        return super.getDisplayRecipes();
    }
    @Override
    public void registerDefaultRecipes() {
        addRecipe(12000, new ItemStack[]{new CustomItemStack(SlimefunItems.ADVANCED_CIRCUIT_BOARD, 8), Items.MONEY_STAMP}, new ItemStack[]{Items.MONEY, Items.MONEY_STAMP});
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
        ItemStack ProgressBar = new ItemStack(Material.ANVIL);
        ProgressBar.getItemMeta().setDisplayName(ChatColor.translateAlternateColorCodes('&', Texts.mp_1));
        return ProgressBar;
    }
}