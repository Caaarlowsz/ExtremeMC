package extrememc.kitpvp.commands;

import extrememc.kitpvp.scoreboard.Scoreboarding;
import extrememc.kitpvp.inventory.ManagerInventory;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.protectionManager.ProtectionManager;
import extrememc.kitpvp.api.warp.WarpsAPI;
import extrememc.common.Main;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class SpawnCommand implements CommandExecutor
{
    public boolean onCommand(final CommandSender commandSender, final Command cmd, final String label, final String[] args) {
        if (commandSender instanceof Player) {
            final Player player = (Player)commandSender;
            if (args.length == 0) {
                new BukkitRunnable() {
                    public void run() {
                        player.getInventory().clear();
                        player.getActivePotionEffects().clear();
                        player.getInventory().setArmorContents((ItemStack[])null);
                        player.setHealth(20.0);
                        player.setFireTicks(0);
                        player.setFlying(false);
                        player.setAllowFlight(false);
                    }
                }.runTaskLater(Main.getPlugin(), 1L);
                new BukkitRunnable() {
                    public void run() {
                        WarpsAPI.setWarp(player, WarpsAPI.Warps.SPAWN);
                        WarpsAPI.goToWarp(player, WarpsAPI.Warps.SPAWN);
                        BuildCommand.Build.put(player.getName(), BuildCommand.BuildModes.OFF);
                        ProtectionManager.setProtection(player);
                    }
                }.runTaskLater(Main.getPlugin(), 2L);
                new BukkitRunnable() {
                    public void run() {
                        KitAPI.removeKit(player);
                        ManagerInventory.sendItens(player);
                        player.sendMessage("§aVoc\u00ea foi teleportado para o Spawn!");
                        Scoreboarding.setScoreboard(player);
                    }
                }.runTaskLater(Main.getPlugin(), 3L);
                return true;
            }
        }
        return false;
    }
}
