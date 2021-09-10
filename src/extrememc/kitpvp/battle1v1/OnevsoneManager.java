package extrememc.kitpvp.battle1v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import extrememc.common.itemBuilder.ItemBuilder;
import extrememc.kitpvp.api.warp.WarpsAPI;
import extrememc.kitpvp.protectionManager.ProtectionManager;
import extrememc.kitpvp.scoreboard.Scoreboarding;

public class OnevsoneManager {
	public static List<UUID> inWarp;
	public static ArrayList<UUID> cooldown;
	public static ArrayList<UUID> invite;
	public static ArrayList<UUID> invited;
	public static HashMap<UUID, Player> fighting;
	public static HashMap<UUID, String> opponent;
	public static List<Player> fast;
	public static ItemStack blaze_rod;
	public static ItemStack gray_color;
	public static ItemStack green_color;

	static {
		OnevsoneManager.inWarp = new ArrayList<UUID>();
		OnevsoneManager.cooldown = new ArrayList<UUID>();
		OnevsoneManager.invite = new ArrayList<UUID>();
		OnevsoneManager.invited = new ArrayList<UUID>();
		OnevsoneManager.fighting = new HashMap<UUID, Player>();
		OnevsoneManager.opponent = new HashMap<UUID, String>();
		OnevsoneManager.fast = new ArrayList<Player>();
		OnevsoneManager.blaze_rod = create(Material.BLAZE_ROD, "§eDesafiar jogador", (short) 0);
		OnevsoneManager.gray_color = create(Material.INK_SACK, "§c1v1 R\u00e1pido", (short) 8);
		OnevsoneManager.green_color = create(Material.INK_SACK, "§a1v1 R\u00e1pido", (short) 10);
	}

	public static ItemStack create(final Material material, final String name, final short color) {
		final ItemStack itemStack = new ItemStack(material, 1, color);
		final ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(name);
		itemMeta.spigot().setUnbreakable(true);
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static void sendItens(final Player player) {
		player.getInventory().clear();
		player.getInventory().setArmorContents((ItemStack[]) null);
		player.getActivePotionEffects().clear();
		if (!OnevsoneManager.inWarp.contains(player.getUniqueId())) {
			OnevsoneManager.inWarp.add(player.getUniqueId());
		}
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
			final Player all = onlinePlayers[i];
			player.showPlayer(all);
		}
		player.getInventory().setItem(3, OnevsoneManager.blaze_rod);
		player.getInventory().setItem(5, OnevsoneManager.gray_color);
		player.updateInventory();
	}

	public static void startMatch(final Player player, final Player player_2) {
		WarpsAPI.goToWarp(player, WarpsAPI.Locais.ONEVSONEPOS1);
		WarpsAPI.goToWarp(player_2, WarpsAPI.Locais.ONEVSONEPOS2);
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, j = 0; j < length; ++j) {
			final Player all = onlinePlayers[j];
			player.hidePlayer(all);
			player_2.hidePlayer(all);
			player.showPlayer(player_2);
			player_2.showPlayer(player);
		}
		player.getInventory().clear();
		player_2.getInventory().clear();
		player.getInventory().setHelmet(create(Material.IRON_HELMET, "", (short) 0));
		player.getInventory().setChestplate(create(Material.IRON_CHESTPLATE, "l", (short) 0));
		player.getInventory().setLeggings(create(Material.IRON_LEGGINGS, "", (short) 0));
		player.getInventory().setBoots(create(Material.IRON_BOOTS, "", (short) 0));
		player_2.getInventory().setHelmet(create(Material.IRON_HELMET, "", (short) 0));
		player_2.getInventory().setChestplate(create(Material.IRON_CHESTPLATE, "", (short) 0));
		player_2.getInventory().setLeggings(create(Material.IRON_LEGGINGS, "", (short) 0));
		player_2.getInventory().setBoots(create(Material.IRON_BOOTS, "", (short) 0));
		for (int i = 0; i < 9; ++i) {
			player.getInventory().addItem(create(Material.MUSHROOM_SOUP, "", (short) 0));
			player_2.getInventory().addItem(create(Material.MUSHROOM_SOUP, "", (short) 0));
		}
		player.getInventory().setItem(0, ItemBuilder.criarItem(Material.DIAMOND_SWORD, "", Enchantment.DAMAGE_ALL, 1));
		player_2.getInventory().setItem(0,
				ItemBuilder.criarItem(Material.DIAMOND_SWORD, "", Enchantment.DAMAGE_ALL, 1));
		player.updateInventory();
		player_2.updateInventory();
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20.0);
		player.setGameMode(GameMode.SURVIVAL);
		player_2.setHealth(20.0);
		OnevsoneManager.fast.remove(player_2);
		OnevsoneManager.fast.remove(player);
		OnevsoneManager.fighting.put(player.getUniqueId(), player_2);
		OnevsoneManager.fighting.put(player_2.getUniqueId(), player);
		OnevsoneManager.invite.remove(player.getUniqueId());
		OnevsoneManager.invited.remove(player_2.getUniqueId());
		OnevsoneManager.cooldown.remove(player.getUniqueId());
		OnevsoneManager.cooldown.remove(player_2.getUniqueId());
		OnevsoneManager.opponent.put(player.getUniqueId(), player_2.getName());
		OnevsoneManager.opponent.put(player_2.getUniqueId(), player.getName());
		ProtectionManager.removeProtection(player);
		ProtectionManager.removeProtection(player_2);
		Scoreboarding.updateOpponent(player, player.getScoreboard());
		Scoreboarding.updateOpponent(player_2, player_2.getScoreboard());
	}
}
