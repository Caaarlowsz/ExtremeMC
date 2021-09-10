package extrememc.kitpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import extrememc.common.Main;
import extrememc.common.adminManager.AdminManager;
import extrememc.common.strings.Strings;

public class AdminCommand implements CommandExecutor {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		final Player player = (Player) sender;
		if (!Main.getInstance().getPermissions().isTrial(player)) {
			player.sendMessage(Strings.permission);
			return true;
		}
		if (args.length == 0) {
			if (AdminManager.hasAdmin(player)) {
				AdminManager.removeAdmin(player);
			} else {
				AdminManager.setAdmin(player);
			}
		}
		return false;
	}
}
