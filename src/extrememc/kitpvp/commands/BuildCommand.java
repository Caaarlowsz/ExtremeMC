package extrememc.kitpvp.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import extrememc.common.Main;
import extrememc.common.strings.Strings;

public class BuildCommand implements Listener, CommandExecutor {
	public static HashMap<String, BuildModes> Build;

	static {
		BuildCommand.Build = new HashMap<String, BuildModes>();
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("");
			return true;
		}
		final Player p = (Player) sender;
		if (!Main.getInstance().getPermissions().isAdmin(p)) {
			sender.sendMessage(Strings.permission);
			return true;
		}
		if (args.length == 0) {
			p.sendMessage("§e§lBUILD §fUtilize: /build (on/off)");
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("on")) {
				if (BuildCommand.Build.get(p.getName()) == BuildModes.ON) {
					p.sendMessage("§e§lBUILD §fSeu modo j\u00e1 est\u00e1 §a§lATIVADO");
					return true;
				}
				p.sendMessage("§e§lBUILD §fSeu modo build foi §a§lATIVADO");
				BuildCommand.Build.put(p.getName(), BuildModes.ON);
				return true;
			} else if (args[0].equalsIgnoreCase("off")) {
				if (BuildCommand.Build.get(p.getName()) == BuildModes.OFF) {
					p.sendMessage("§e§lBUILD §fSeu modo j\u00e1 est\u00e1 §c§lDESATIVADO");
					return true;
				}
				p.sendMessage("§e§lBUILD §fSeu modo build foi §c§lDESATIVADO");
				BuildCommand.Build.put(p.getName(), BuildModes.OFF);
				return true;
			}
		}
		return false;
	}

	public enum BuildModes {
		ON("ON", 0), OFF("OFF", 1);

		BuildModes(final String s, final int n) {
		}
	}
}
