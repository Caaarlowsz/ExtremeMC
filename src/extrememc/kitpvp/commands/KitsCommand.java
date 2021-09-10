package extrememc.kitpvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import extrememc.common.json.JSONChatClickEventType;
import extrememc.common.json.JSONChatColor;
import extrememc.common.json.JSONChatExtra;
import extrememc.common.json.JSONChatHoverEventType;
import extrememc.common.json.JSONChatMessage;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import extrememc.kitpvp.api.warp.WarpsAPI;

public class KitsCommand implements CommandExecutor {
	public boolean onCommand(final CommandSender commandSender, final Command command, final String label,
			final String[] args) {
		if (!(commandSender instanceof Player)) {
			commandSender.sendMessage("§c§lERRO §fApenas jogadores possuem acesso \u00e0 este comando!");
			return true;
		}
		final Player player = (Player) commandSender;
		if (args.length == 0) {
			this.sendKits(player);
			return true;
		}
		if (!KitAPI.hasKit(player, Kits.NENHUM)) {
			player.sendMessage("§c§lERRO §fVoc\u00ea j\u00e1 esta com kit selecionado!");
			return true;
		}
		if (!WarpsAPI.isInWarp(player, WarpsAPI.Warps.SPAWN)) {
			player.sendMessage("§c§lERRO §fVoc\u00ea n\u00e3o pode selecionar kit nesse local!");
			return true;
		}
		if (args.length != 1) {
			return false;
		}
		final String kitName = args[0].toUpperCase();
		Kits kit = Kits.PVP;
		try {
			kit = Kits.valueOf(kitName);
		} catch (Exception exception) {
			kit = Kits.PVP;
			player.sendMessage("§c§lERRO §fN\u00e3o foi poss\u00edvel encontrar o Kit especificado.");
			return true;
		}
		if (!KitAPI.hasKitPermission(player, kit)) {
			player.sendMessage("§e§lKIT §fVoc\u00ea n\u00e3o possui acesso \u00e0 este Kit.");
			return true;
		}
		KitAPI.setKit(player, kit);
		KitAPI.giveItens(player);
		KitAPI.giveKit(player, kit);
		player.sendMessage("§aVoc\u00ea selecionou o kit §f" + kit.name() + "§a!");
		return true;
	}

	public JSONChatExtra create(final Player player, final Kits kit) {
		final JSONChatExtra extra = new JSONChatExtra("§f, §e" + kit.getName());
		extra.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT,
				String.valueOf(kit.getName()) + "\n" + "\n" + "§a§nClique para selecionar!");
		extra.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/kit " + kit.name());
		return extra;
	}

	public void sendKits(final Player player) {
		int q = 0;
		Kits[] values;
		for (int length = (values = Kits.values()).length, i = 0; i < length; ++i) {
			final Kits kits = values[i];
			if (KitAPI.hasKitPermission(player, kits)) {
				++q;
			}
		}
		JSONChatMessage message;
		if (q < 19) {
			message = new JSONChatMessage(
					"§fKits que voc\u00ea possui (§c" + q + "§7/§a" + Kits.values().length + "§f): ",
					(JSONChatColor) null, null);
		} else {
			message = new JSONChatMessage(
					"§fKits que voc\u00ea possui (§a" + q + "§7/§a" + Kits.values().length + "§f): ",
					(JSONChatColor) null, null);
		}
		final JSONChatExtra membro = new JSONChatExtra("§e" + Kits.NENHUM.getName());
		membro.setHoverEvent(JSONChatHoverEventType.SHOW_TEXT,
				"§e" + Kits.NENHUM.getName() + "\n" + "\n" + "§a§nClique para selecionar!");
		membro.setClickEvent(JSONChatClickEventType.RUN_COMMAND, "/kit nenhum");
		message.addExtra(membro);
		Kits[] arrayOfKits;
		int a;
		int b;
		Kits kits2;
		JSONChatExtra extra;
		for (a = (arrayOfKits = Kits.values()).length, b = (arrayOfKits = Kits.values()).length, a = 0; a < b; ++a) {
			kits2 = arrayOfKits[a];
			if (!kits2.equals(Kits.NENHUM) && KitAPI.hasKitPermission(player, kits2)) {
				extra = this.create(player, kits2);
				message.addExtra(extra);
			}
		}
		message.sendToPlayer(player);
		player.sendMessage("§7§nDICA§7: Clique em cima do kit desejado para seleciona-lo.");
	}
}
