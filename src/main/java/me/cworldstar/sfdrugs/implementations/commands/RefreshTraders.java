package me.cworldstar.sfdrugs.implementations.commands;

import me.cworldstar.sfdrugs.SFDrugs;
import me.cworldstar.sfdrugs.implementations.traders.ATrader;
import me.cworldstar.sfdrugs.utils.Constants;
import me.cworldstar.sfdrugs.utils.Texts;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RefreshTraders implements CommandExecutor {
    private final SFDrugs plugin;

    public RefreshTraders(SFDrugs plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = sender.getServer().getPlayer(sender.getName());
            if (p.isOp()) {
                if (args[0].equals(Constants.all)) {
                    for (ATrader Trader : ATrader.Traders.values()) {
                        // completely reset all trading interfaces
                        Trader.getTradingInterface().PopulateInventory();
                        return true;
                    }
                } else {
                    ATrader Trader = ATrader.Traders.get(args[0]);
                    if (!Trader.equals(null)) {
                        Trader.getTradingInterface().PopulateInventory();
                        return true;
                    }
                }

            } else {
                sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + Texts.cmd_rt_1);
                return false;
            }
        }
        return false;
    }

}
