package me.cworldstar.sfdrugs.implementations.commands;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.bosses.CorporationMobZone;
import me.cworldstar.sfdrugs.implementations.bosses.entities.CorporateLeader.CorporateLeader;
import me.cworldstar.sfdrugs.implementations.bosses.entities.CorporateScout;
import me.cworldstar.sfdrugs.implementations.bosses.entities.CorporateWorker;
import me.cworldstar.sfdrugs.implementations.bosses.entities.EscapedTestSubject;
import me.cworldstar.sfdrugs.implementations.bosses.entities.GangMember;
import me.cworldstar.sfdrugs.implementations.bosses.entities.SmallerGangMember;
import me.cworldstar.sfdrugs.implementations.gui.ATradingInterface;
import me.cworldstar.sfdrugs.implementations.gui.ATradingInterface.InventorySize;
import me.cworldstar.sfdrugs.implementations.traders.ATrader;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.RandomUtils;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class TestCorporationEnemy implements CommandExecutor {
    private final SFDrugs plugin;

    public TestCorporationEnemy(SFDrugs plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.isOp()) {
                switch (args[0].toLowerCase()) {
                    case Constants.corporate_security_robot:
                        new CorporationMobZone(plugin, p.getWorld(), p.getLocation());
                        break;
                    case Constants.red_wolves_gangster:
                        new GangMember(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;
                    case Constants.escaped_test_subject:
                        new EscapedTestSubject(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;

                    case Constants.red_wolves_trainee:
                        new SmallerGangMember(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;
                    case Constants.corporate_worker:
                        new CorporateWorker(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;
                    case Constants.corporate_scout:
                        new CorporateScout(plugin, p.getWorld(), p.getLocation());
                        break;
                    case Constants.corporate_leader:
                        new CorporateLeader(p.getWorld(), p.getLocation());
                        break;
                    case Constants.inventory:
                        ATradingInterface test = new ATradingInterface(InventorySize.LARGE, new ItemStack(Material.BLACK_STAINED_GLASS_PANE), ATrader.Traders.get(Constants.corporation_trader));
                        test.Display(p);
                        break;
                    case Constants.fix_drug:
                        p.getPersistentDataContainer().remove(SFDrugs.createKey(Constants.methamphetamine_overdosing));
                        break;
                    case Constants.generate:
                        Chunk ChunkToGenerate = p.getWorld().getChunkAt(RandomUtils.RandomLocation(p.getWorld(), 5000, 0, 5000));
                        if (ChunkToGenerate.getInhabitedTime() == 0 && ChunkToGenerate.getPersistentDataContainer().get(SFDrugs.createKey(Constants.spawn_corporate_building), PersistentDataType.STRING) == null) {
                            ChunkToGenerate.getPersistentDataContainer().set(SFDrugs.createKey(Constants.spawn_corporate_building), PersistentDataType.STRING, args[0]);
                            p.sendMessage(Texts.cmd_tce_1 + ChunkToGenerate.getX() + "X, " + ChunkToGenerate.getZ() + "Y.");
                        } else {
                            p.sendMessage(Texts.cmd_tce_2);
                        }
                        p.sendMessage(label);
                        break;
                    case Constants.getposinchunk:
                        Location betterLoc = p.getLocation().subtract(new Location(p.getWorld(), p.getLocation().getChunk().getX(), p.getLocation().getY(), p.getLocation().getChunk().getZ()));
                        p.sendMessage(betterLoc + Texts.cmd_tce_3 + betterLoc.getX() + "X, " + betterLoc.getY() + "Y, " + betterLoc.getZ());
                        break;
                    default:
                        sender.sendMessage(Texts.cmd_tce_4);
                        break;
                }
                return true;
            }
        }
        return false;
    }

}
