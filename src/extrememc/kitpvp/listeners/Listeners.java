package extrememc.kitpvp.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import extrememc.common.adminManager.AdminManager;
import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import extrememc.kitpvp.api.warp.WarpsAPI;
import extrememc.kitpvp.commands.BuildCommand;
import extrememc.kitpvp.inventory.ManagerInventory;
import extrememc.kitpvp.protectionManager.ProtectionManager;
import extrememc.kitpvp.scoreboard.Scoreboarding;

public class Listeners implements Listener {
	@EventHandler
	public void asd(final PlayerJoinEvent event) {
		event.setJoinMessage((String) null);
		final Player player = event.getPlayer();
		new BukkitRunnable() {
			public void run() {
				player.getInventory().clear();
				player.getActivePotionEffects().clear();
				player.getInventory().setArmorContents((ItemStack[]) null);
				player.getInventory().remove(Material.COMPASS);
				player.setGameMode(GameMode.SURVIVAL);
				player.setAllowFlight(false);
				player.setHealth(20.0);
				player.setFoodLevel(20);
				player.setFireTicks(0);
			}
		}.runTaskLater(ExtremePvP.getPlugin(), 1L);
		new BukkitRunnable() {
			public void run() {
				WarpsAPI.setWarp(player, WarpsAPI.Warps.SPAWN);
				WarpsAPI.goToWarp(player, WarpsAPI.Warps.SPAWN);
				BuildCommand.Build.put(player.getName(), BuildCommand.BuildModes.OFF);
				ProtectionManager.setProtection(player);
			}
		}.runTaskLater(ExtremePvP.getPlugin(), 2L);
		new BukkitRunnable() {
			public void run() {
				ExtremePvP.scoreboard.add(player.getUniqueId());
				Scoreboarding.setScoreboard(player);
				KitAPI.removeKit(player);
				ManagerInventory.sendItens(player);
				for (int i = 1; i < 100; ++i) {
					player.sendMessage(" ");
				}
				player.sendMessage("�aSeja bem-vindo ao WavePvP, selecione seu kit");
				player.sendMessage("�ae entre em nossa batalha, boa sorte!");
			}
		}.runTaskLater(ExtremePvP.getPlugin(), 3L);
		new BukkitRunnable() {
			public void run() {
				if (extrememc.common.Main.getInstance().getPermissions().isTrial(player)) {
					if (AdminManager.hasAdmin(player)) {
						AdminManager.adminMode.remove(player);
						AdminManager.setAdmin(player);
					} else {
						AdminManager.setAdmin(player);
					}
				}
			}
		}.runTaskLater(ExtremePvP.getInstance(), 20L);
	}

	@EventHandler
	public void asd(final PlayerQuitEvent event) {
		event.setQuitMessage((String) null);
		final Player player = event.getPlayer();
		ExtremePvP.scoreboard.remove(player.getUniqueId());
		Scoreboarding.removeScoreboard(player);
	}

	@EventHandler
	public void onPickup(final PlayerPickupItemEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onBreak(final BlockBreakEvent e) {
		final Player p = e.getPlayer();
		if (BuildCommand.Build.get(p.getName()) == BuildCommand.BuildModes.OFF) {
			e.setCancelled(true);
		} else {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void onPlace(final BlockPlaceEvent e) {
		final Player p = e.getPlayer();
		if (e.getBlock().getType() != Material.COBBLESTONE) {
			e.setCancelled(true);
		} else {
			e.setCancelled(false);
		}
		if (BuildCommand.Build.get(p.getName()) == BuildCommand.BuildModes.OFF) {
			e.setCancelled(true);
		} else {
			e.setCancelled(false);
		}
	}

	@EventHandler
	public void onDrop(final PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getType() != Material.MUSHROOM_SOUP
				&& e.getItemDrop().getItemStack().getType() != Material.BOWL
				&& e.getItemDrop().getItemStack().getType() != Material.ENDER_PEARL
				&& e.getItemDrop().getItemStack().getType() != Material.RED_MUSHROOM
				&& e.getItemDrop().getItemStack().getType() != Material.BROWN_MUSHROOM
				&& e.getItemDrop().getItemStack().getType() != Material.IRON_HELMET
				&& e.getItemDrop().getItemStack().getType() != Material.IRON_CHESTPLATE
				&& e.getItemDrop().getItemStack().getType() != Material.IRON_LEGGINGS
				&& e.getItemDrop().getItemStack().getType() != Material.IRON_BOOTS
				&& e.getItemDrop().getItemStack().getType() != Material.GLASS_BOTTLE) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			final Player d = (Player) e.getDamager();
			if (d.getItemInHand().getType() == Material.DIAMOND_SWORD
					|| d.getItemInHand().getType() == Material.STONE_SWORD
					|| d.getItemInHand().getType() == Material.WOOD_SWORD
					|| d.getItemInHand().getType() == Material.STONE_SWORD
					|| d.getItemInHand().getType() == Material.IRON_SWORD
					|| d.getItemInHand().getType() == Material.GOLD_SWORD
					|| d.getItemInHand().getType() == Material.FISHING_ROD
					|| d.getItemInHand().getType() == Material.DIAMOND_AXE
					|| d.getItemInHand().getType() == Material.GOLD_AXE
					|| d.getItemInHand().getType() == Material.STONE_AXE
					|| d.getItemInHand().getType() == Material.WOOD_AXE
					|| d.getItemInHand().getType() == Material.IRON_AXE) {
				d.getItemInHand().setDurability((short) 0);
				d.updateInventory();
			}
		}
	}

	@EventHandler
	public void asd(final WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void asd(final FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void asd(final ItemSpawnEvent e) {
		e.getEntity().remove();
	}

	@EventHandler
	public void asd(final CreatureSpawnEvent e) {
		if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onExplode(final EntityExplodeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void asd(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		Player c = null;
		if (p.getItemInHand().getType().equals(Material.COMPASS)) {
			for (final Entity et : p.getNearbyEntities(500.0, 256.0, 500.0)) {
				if (et instanceof Player && et.getLocation().distance(p.getLocation()) > 20.0) {
					c = (Player) et;
					break;
				}
			}
			if (KitAPI.hasKit(p, Kits.NENHUM) || KitAPI.hasKit(c, Kits.NENHUM)) {
				return;
			}
			if (c == null) {
				p.sendMessage("�c* �7Nenhum jogador encontrado, bussola apotando para �bSpawn");
				p.setCompassTarget(p.getWorld().getSpawnLocation());
			} else {
				p.sendMessage("�a* �7Bussola apotando para �b" + c.getName());
				p.setCompassTarget(c.getLocation());
			}
		}
	}

	@EventHandler
	public void soup(final PlayerInteractEvent e) {
		final Damageable hp;
		final Player p = (Player) (hp = e.getPlayer());
		if (hp.getHealth() != 20.0
				&& (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& p.getItemInHand().getTypeId() == 282) {
			p.setHealth((hp.getHealth() + 7.0 > hp.getMaxHealth()) ? hp.getMaxHealth() : (hp.getHealth() + 7.0));
			p.getItemInHand().setType(Material.BOWL);
		}
	}
}
