package extrememc.kitpvp.api.warp;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import extrememc.common.adminManager.AdminManager;
import extrememc.common.itemBuilder.ItemBuilder;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import extrememc.kitpvp.battle1v1.OnevsoneManager;
import extrememc.kitpvp.inventory.ManagerInventory;
import extrememc.kitpvp.protectionManager.ProtectionManager;
import extrememc.kitpvp.scoreboard.Scoreboarding;

public class WarpManager implements Listener {
	public static void send(final Player player, final WarpsAPI.Warps warp) {
		KitAPI.removeKit(player);
		player.getActivePotionEffects().clear();
		player.getInventory().setArmorContents((ItemStack[]) null);
		player.getInventory().remove(Material.COMPASS);
		player.updateInventory();
		player.setGameMode(GameMode.SURVIVAL);
		player.setAllowFlight(false);
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		ProtectionManager.setProtection(player);
		switch (warp) {
		case SCREENSHARE: {
		}
		case SPAWN: {
			player.getInventory().clear();
			WarpsAPI.setWarp(player, WarpsAPI.Warps.SPAWN);
			WarpsAPI.goToWarp(player, WarpsAPI.Warps.SPAWN);
			ManagerInventory.sendItens(player);
		}
		case FPS: {
			player.getInventory().clear();
			player.getInventory().setItem(13, ItemBuilder.criarItem(Material.RED_MUSHROOM, "§aCogumelo Vermelho", 64));
			player.getInventory().setItem(14, ItemBuilder.criarItem(Material.BROWN_MUSHROOM, "§aCogumelo Marrom", 64));
			player.getInventory().setItem(15, ItemBuilder.criarItem(Material.BOWL, "§aPote", 64));
			for (int i = 0; i < 34; ++i) {
				player.getInventory().addItem(ItemBuilder.criarItem(Material.MUSHROOM_SOUP));
			}
			player.getInventory().setItem(0, ItemBuilder.criarItem(Material.DIAMOND_SWORD, Enchantment.DAMAGE_ALL, 1));
			player.getInventory().setHelmet(ItemBuilder.criarItem(Material.IRON_HELMET));
			player.getInventory().setChestplate(ItemBuilder.criarItem(Material.IRON_CHESTPLATE));
			player.getInventory().setLeggings(ItemBuilder.criarItem(Material.IRON_LEGGINGS));
			player.getInventory().setBoots(ItemBuilder.criarItem(Material.IRON_BOOTS));
			player.updateInventory();
			WarpsAPI.setWarp(player, WarpsAPI.Warps.FPS);
			WarpsAPI.goToWarp(player, WarpsAPI.Warps.FPS);
			break;
		}
		case ONEVSONE: {
			player.getInventory().clear();
			OnevsoneManager.fighting.remove(player.getUniqueId());
			OnevsoneManager.opponent.remove(player.getUniqueId());
			WarpsAPI.setWarp(player, WarpsAPI.Warps.ONEVSONE);
			WarpsAPI.goToWarp(player, WarpsAPI.Warps.ONEVSONE);
			OnevsoneManager.sendItens(player);
			break;
		}
		default:
			break;
		}
		Scoreboarding.setScoreboard(player);
	}

	@EventHandler
	public void asd(final PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.SPAWN)) {
			if (AdminManager.hasAdmin(player)) {
				return;
			}
			final Location loc = new Location(Bukkit.getWorld("world"), WarpsAPI.getInConfigLocationIntX("spawn"),
					WarpsAPI.getInConfigLocationIntY("spawn"), WarpsAPI.getInConfigLocationIntZ("spawn"));
			if (player.getLocation().distance(loc) >= 16.0) {
				KitAPI.setKit(player, Kits.PVP);
				KitAPI.giveItens(player);
			}
		}
		if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.FPS) && ProtectionManager.hasProtection(player)) {
			final Location loc = new Location(Bukkit.getWorld("world"), WarpsAPI.getInConfigLocationIntX("fps"),
					WarpsAPI.getInConfigLocationIntY("fps"), WarpsAPI.getInConfigLocationIntZ("fps"));
			if (player.getLocation().distance(loc) >= 10.0) {
				ProtectionManager.removeProtection(player);
			}
		}
	}
}
