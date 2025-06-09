package me.cworldstar.sfdrugs.implementations.items;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class UnstableObject extends SlimefunItem {
    private static SFDrugs plugin;
    public final Unstable unstable;

    public UnstableObject(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType,
                          ItemStack[] recipe, Unstable unstable, SFDrugs plugin) {
        super(itemGroup, item, id, recipeType, recipe);
        this.unstable = unstable;
        UnstableObject.plugin = plugin;
        item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(UnstableObject.plugin, Constants.Unstable), PersistentDataType.DOUBLE, UnstableObject.getCooldown(this.unstable));
        // TODO Auto-generated constructor stub
    }

    public UnstableObject(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
                          ItemStack[] recipe, Unstable unstable, SFDrugs plugin) {
        super(itemGroup, item, recipeType, recipe);
        this.unstable = unstable;
        UnstableObject.plugin = plugin;
        item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(UnstableObject.plugin, Constants.Unstable), PersistentDataType.DOUBLE, UnstableObject.getCooldown(this.unstable));
        // TODO Auto-generated constructor stub
    }

    public static double getCooldown(Unstable unstable) {

        return switch (unstable) {
            case STABLE -> 0.0;
            case SLIGHTLY_UNSTABLE -> 15.0;
            case UNSTABLE -> 15.0;
            case HIGHLY_UNSTABLE -> 10.0;
            default -> 0.0;
        };

    }

    public Unstable getUnstableAmount() {
        return this.unstable;
    }

    public enum Unstable {
        /**
         * Will not explode.
         */
        STABLE(1),
        /**
         * Will not explode, but will vanish after 30 seconds.
         */
        SLIGHTLY_UNSTABLE(2),
        /**
         * Will explode after 30 seconds.
         */
        UNSTABLE(3),

        /**
         * Will explode after 10 seconds.
         */
        HIGHLY_UNSTABLE(4);
        private final int UNSTABLE_VALUE;

        Unstable(int e) {
            this.UNSTABLE_VALUE = e;
        }

        public static Unstable getUnstableFromInteger(int unstable) {
            for (Unstable e2 : values()) {
                if (e2.UNSTABLE_VALUE == unstable) {
                    return e2;
                }
            }
            return STABLE;
        }


    }
}
