package me.cworldstar.sfdrugs.events;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.Speak;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class CustomMobTargetedEvent implements Listener {

    private final SFDrugs plugin;

    public CustomMobTargetedEvent(SFDrugs plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onCustomMobTargetedEvent(EntityTargetEvent e) {
        if (e.getEntity().hasMetadata(Constants.SfDrugsCustomMob)) {
            if (e.getEntity().getMetadata(Constants.SfDrugsCustomMob).get(0).asString().toUpperCase().equals(Constants.CorporateWorker)) {
                new Speak(e.getEntity(), Texts.cmte_1, 20);
            }
        }
    }

}
