package me.cworldstar.sfdrugs.implementations.generators;

import io.github.mooy1.infinitylib.machines.AbstractMachineBlock;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class DarkMatterGenerator extends AbstractMachineBlock implements DarkMatter, EnergyNetProvider {

    private final SFDrugs plugin;
    public long DarkMatter = 0L;

    public DarkMatterGenerator(SFDrugs plugin, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType,
                               ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        if (item.getItem() instanceof Block) {
            ((Block) item).setMetadata(Constants.dark_matter_generator, new FixedMetadataValue(plugin, true));
        }
        this.plugin = plugin;
    }

    @Override
    public boolean process(Block b, BlockMenu menu) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getStatusSlot() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setup(BlockMenuPreset preset) {
        // TODO Auto-generated method stub

    }

    @Override
    public int[] getInputSlots() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getOutputSlots() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getGeneratedOutput(Location l, Config data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public SFDrugs getPlugin() {
        // TODO Auto-generated method stub
        return plugin;
    }


    @Override
    public void onExplode(Block b) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onBreakdown(Block b, double integrity) {
        // TODO Auto-generated method stub

    }

}
