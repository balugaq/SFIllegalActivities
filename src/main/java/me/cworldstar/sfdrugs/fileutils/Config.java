package me.cworldstar.sfdrugs.fileutils;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Config {
    public static final Map<String, File> SchematicDatabase = new HashMap<>();

    public Config(SFDrugs plugin) {
        File cfig = plugin.getDataFolder();
        if (cfig.exists()) {

        } else {
            for (final File f : cfig.listFiles()) {
                if (f.getName().split("/[,.]/")[1].equals(Constants.schem)) {
                    this.register(f.getName().split("/[,.]/")[0], f);
                }
            }
        }
    }

    private void register(String string, File schematic) {
        Config.SchematicDatabase.put(string, schematic);
    }
}
