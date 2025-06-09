package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectionType;
import io.github.thebusybiscuit.slimefun4.core.attributes.ProtectiveArmor;
import io.github.thebusybiscuit.slimefun4.implementation.items.armor.SlimefunArmorPiece;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlatedHazmat extends SlimefunArmorPiece implements ProtectiveArmor {
    private final SFDrugs plugin;

    public PlatedHazmat(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe,
                        PotionEffect[] effects) {
        super(itemGroup, item, recipeType, recipe, effects);
        this.plugin = plugin;
        // TODO Auto-generated constructor stub
    }

    public static SlimefunItemStack getByInteger(int i) {
        return switch (i) {
            case 0 ->
                    new SlimefunItemStack("SFDRUGS_PLATED_HAZMAT_HELMET", new ItemStack(Material.NETHERITE_HELMET), "&7Plated Gas Mask", "", "&6Full set effects:", "&e- Radiation Immunity", "&e- Bee Sting Immunity", "&e- More Armor!");
            case 1 ->
                    new SlimefunItemStack("SFDRUGS_PLATED_HAZMAT_CHESTPLATE", new ItemStack(Material.NETHERITE_CHESTPLATE), "&7Plated Hazmat Chestplate", "", "&6Full set effects:", "&e- Radiation Immunity", "&e- Bee Sting Immunity", "&e- More Armor!");
            case 2 ->
                    new SlimefunItemStack("SFDRUGS_PLATED_HAZMAT_LEGGINGS", new ItemStack(Material.NETHERITE_LEGGINGS), "&7Plated Hazmat Leggings", "", "&6Full set effects:", "&e- Radiation Immunity", "&e- Bee Sting Immunity", "&e- More Armor!");
            case 3 ->
                    new SlimefunItemStack("SFDRUGS_PLATED_HAZMAT_BOOTS", new ItemStack(Material.NETHERITE_BOOTS), "&7Plated Hazmat Boots", "", "&6Full set effects:", "&e- Radiation Immunity", "&e- Bee Sting Immunity", "&e- More Armor!");
            default -> new SlimefunItemStack("SFDRUGS_ERROR", new ItemStack(Material.BARRIER));
        };
    }

    @Override
    public ProtectionType[] getProtectionTypes() {
        // TODO Auto-generated method stub
        return new ProtectionType[]{ProtectionType.RADIATION};
    }

    @Override
    public boolean isFullSetRequired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public NamespacedKey getArmorSetId() {
        // TODO Auto-generated method stub
        return new NamespacedKey(this.plugin, Constants.SfDrugsPlatedHazmat);
    }


}
