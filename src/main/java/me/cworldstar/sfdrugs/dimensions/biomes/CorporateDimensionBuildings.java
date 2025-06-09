package me.cworldstar.sfdrugs.dimensions.biomes;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import me.cworldstar.sfdrugs.SFDrugs;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.persistence.PersistentDataType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class CorporateDimensionBuildings implements Listener {

    private static final Map<String, File> Buildings = new HashMap<>();
    private static final ArrayList<String> BuildingIds = new ArrayList<>();
    private static final Map<String, BuildingConstraint> BuildingConstraints = new HashMap<>();

    public CorporateDimensionBuildings() {
        SFDrugs.getPlugin(SFDrugs.class).getServer().getPluginManager().registerEvents(this, SFDrugs.getPlugin(SFDrugs.class));
    }

    public void RegisterBuilding(String buildingId, File buildingFile, BuildingConstraint constraint) {
        SFDrugs.log(Level.INFO, "Building registered! ID: " + buildingId);
        Buildings.put(buildingId, buildingFile);
        BuildingIds.add(buildingId);
        BuildingConstraints.put(buildingId, constraint);
    }

    public BuildingConstraint getBuildingConstraint(String buildingid) {
        return BuildingConstraints.get(buildingid);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChunkGenerate(ChunkLoadEvent e) throws WorldEditException {
        if (e.getChunk().getPersistentDataContainer().has(SFDrugs.createKey("spawn_corporate_building"), PersistentDataType.STRING)) {


            //TODO: finish building constraints lol
            String building_id = e.getChunk().getPersistentDataContainer().get(SFDrugs.createKey("spawn_corporate_building"), PersistentDataType.STRING);
            //BuildingConstraint Constraint = this.getBuildingConstraint(building_id);
            //if(Constraint.validate(e.getChunk())) {
            ////
            //}

            ClipboardFormat format = ClipboardFormats.findByFile(Buildings.get(building_id));
            try (ClipboardReader reader = format.getReader(new FileInputStream(Buildings.get(building_id)))) {
                EditSession Session = WorldEdit.getInstance().newEditSession((World) e.getWorld());
                Clipboard clipboard = reader.read();
                clipboard.close();
                Session.close();
                reader.close();
                Operation o = new ClipboardHolder(clipboard)
                        .createPaste(Session)
                        .to(BlockVector3.at(e.getChunk().getX(), e.getWorld().getHighestBlockYAt(new Location(e.getWorld(), e.getChunk().getX(), 0, e.getChunk().getZ())), e.getChunk().getZ()))
                        .ignoreAirBlocks(false)
                        .copyEntities(true)
                        .build();
                Operations.complete(o);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }
}
