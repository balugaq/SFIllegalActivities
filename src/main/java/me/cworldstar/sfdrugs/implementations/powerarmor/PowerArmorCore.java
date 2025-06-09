package me.cworldstar.sfdrugs.implementations.powerarmor;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.NotImplementedYet;
import me.cworldstar.sfdrugs.utils.Constants;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class PowerArmorCore extends SlimefunItem implements Rechargeable, NotImplementedYet {


    public static final ArrayList<PowerArmorCore> Cores = new ArrayList<>();

    public PowerArmorCore(ItemGroup itemGroup, ItemStack item, String id, RecipeType recipeType, ItemStack[] recipe, String core_id) {
        super(itemGroup, item, id, recipeType, recipe);
        // TODO Auto-generated constructor stub
        this.getItem().getItemMeta().getPersistentDataContainer().set(SFDrugs.createKey(Constants.power_armor_core), PersistentDataType.STRING, core_id);
        PowerArmorCore.Cores.add(this);
    }


    @Override
    public float getMaxItemCharge(ItemStack item) {
        // TODO Auto-generated method stub
        return 0;
    }

}
