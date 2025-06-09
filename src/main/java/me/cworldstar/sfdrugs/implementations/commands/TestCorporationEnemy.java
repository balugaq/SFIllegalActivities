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
import me.cworldstar.sfdrugs.utils.RandomUtils;
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
        if (sender instanceof Player) {
            Player p = sender.getServer().getPlayer(sender.getName());
            if (p.isOp()) {
                switch (args[0].toLowerCase()) {
                    case "corporate_security_robot":
                        new CorporationMobZone(plugin, p.getWorld(), p.getLocation());
                        break;
                    case "red_wolves_gangster":
                        new GangMember(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;
                    case "escaped_test_subject":
                        new EscapedTestSubject(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;

                    case "red_wolves_trainee":
                        new SmallerGangMember(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;
                    case "corporate_worker":
                        new CorporateWorker(plugin, (Zombie) p.getWorld().spawnEntity(p.getLocation(), EntityType.ZOMBIE));
                        break;
                    case "corporate_scout":
                        new CorporateScout(plugin, p.getWorld(), p.getLocation());
                        break;
                    case "corporate_leader":
                        new CorporateLeader(p.getWorld(), p.getLocation());
                        break;
                    case "inventory":
                        ATradingInterface test = new ATradingInterface(InventorySize.LARGE, new ItemStack(Material.BLACK_STAINED_GLASS_PANE), ATrader.Traders.get("corporate_trader"));
                        test.Display(p);
                        break;
                    case "fix_drug":
                        p.getPersistentDataContainer().remove(SFDrugs.createKey("methamphetamine_overdosing"));
                        break;
                    case "generate":
                        Chunk ChunkToGenerate = p.getWorld().getChunkAt(RandomUtils.RandomLocation(p.getWorld(), 5000, 0, 5000));
                        if (ChunkToGenerate.getInhabitedTime() == 0 && ChunkToGenerate.getPersistentDataContainer().get(SFDrugs.createKey("spawn_corporate_building"), PersistentDataType.STRING) == null) {
                            ChunkToGenerate.getPersistentDataContainer().set(SFDrugs.createKey("spawn_corporate_building"), PersistentDataType.STRING, args[0]);
                            p.sendMessage("Chunk inhabited at " + Integer.toString(ChunkToGenerate.getX()).concat("X, ") + ChunkToGenerate.getZ() + "Y.");
                        } else {
                            p.sendMessage("Chunk was inhabited. Could not flag. Try again.");
                        }
                        p.sendMessage(label);
                        break;
                    case "getposinchunk":
                        Location betterLoc = p.getLocation().subtract(new Location(p.getWorld(), p.getLocation().getChunk().getX(), p.getLocation().getY(), p.getLocation().getChunk().getZ()));
                        p.sendMessage(betterLoc + " Raw. Stringified loc: " + betterLoc.getX() + "X, " + betterLoc.getY() + "Y, " + betterLoc.getZ());
                        break;
                    default:
                        sender.sendMessage("Invalid test parameter.");
                        break;
                }
                return true;
            }
        }
        return false;
    }

}
