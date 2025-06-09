package me.cworldstar.sfdrugs.implementations;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.inventory.ItemStack;

public class TradingRecipe {
    private final CustomItemStack Have;
    private final CustomItemStack For;

    public TradingRecipe(ItemStack Have, int HaveAmt, ItemStack For, int ForAmt) {
        this.Have = new CustomItemStack(Have, HaveAmt);
        this.For = new CustomItemStack(For, ForAmt);
    }

    public ItemStack getHave() {
        return this.Have;
    }

    public ItemStack getFor() {
        return this.For;
    }

    public TradingRecipe getTradingRecipe() {
        return this;

    }

    public boolean Validate() {
        return this.Have.getAmount() < 65 & this.For.getAmount() < 65;
    }
}
