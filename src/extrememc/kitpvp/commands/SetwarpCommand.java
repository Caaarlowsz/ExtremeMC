package extrememc.kitpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import extrememc.common.Main;
import extrememc.common.strings.Strings;
import extrememc.kitpvp.api.warp.WarpsAPI;

public class SetwarpCommand implements CommandExecutor {
	public boolean onCommand(final CommandSender commandSender, final Command command, final String label,
			final String[] args) {
		if (commandSender instanceof Player) {
			final Player player = (Player) commandSender;
			if (!Main.getInstance().getPermissions().isBuilder(player)) {
				player.sendMessage(Strings.permission);
				return true;
			}
			if (args.length == 0) {
				player.sendMessage("");
				player.sendMessage("§3§lSETAR");
				player.sendMessage("");
				player.sendMessage("§f/set (spawn) §8- §7Para setar o spawn.");
				player.sendMessage("§f/set (fps) §8- §7Para setar a fps.");
				player.sendMessage("§f/set (onevsonepos1) §8- §7Para setar a posi\u00e7\u00e3o do jogador 1.");
				player.sendMessage("§f/set (onevsonepos2) §8- §7Para setar a posi\u00e7\u00e3o do jogador 2.");
				player.sendMessage("");
				return true;
			}
			if (args.length == 1) {
				try {
					final WarpsAPI.Warps warp = WarpsAPI.Warps.valueOf(args[0].toUpperCase());
					WarpsAPI.setWarpLocation(player, warp);
					player.sendMessage("§3§lSET §fVoc\u00ea §a§lSETOU§f a warp §c" + warp.name().toUpperCase() + "§f.");
					return true;
				} catch (Exception exception) {
					try {
						final WarpsAPI.Locais local = WarpsAPI.Locais.valueOf(args[0].toUpperCase());
						WarpsAPI.setWarpLocation(player, local);
						player.sendMessage(
								"§3§lSET §fVoc\u00ea §a§lSETOU§f o local §c" + local.name().toUpperCase() + "§f.");
						return true;
					} catch (Exception exception2) {
						player.sendMessage("§c§lERRO §fA warp citada n\u00e3o foi encontrada!");
						return true;
					}
				}
			}
		}
		return false;
	}
}
