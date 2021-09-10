package extrememc.kitpvp.listeners;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import extrememc.common.clanManager.Clan;
import extrememc.common.playerManager.PlayerManager;
import extrememc.common.strings.Strings;
import extrememc.kitpvp.Main;
import extrememc.kitpvp.api.hability.kits.Gladiator;
import extrememc.kitpvp.api.warp.WarpManager;
import extrememc.kitpvp.api.warp.WarpsAPI;
import extrememc.kitpvp.battle1v1.OnevsoneManager;
import extrememc.kitpvp.protectionManager.ProtectionManager;
import extrememc.kitpvp.scoreboard.Scoreboarding;

public class MorreuListener implements Listener {
	public int itemsInInventory(final Inventory inventory, final Material[] search) {
		final List<Material> wanted = Arrays.asList(search);
		int found = 0;
		ItemStack[] arrayOfItemStack;
		for (int j = (arrayOfItemStack = inventory.getContents()).length, i = 0; i < j; ++i) {
			final ItemStack item = arrayOfItemStack[i];
			if (item != null && wanted.contains(item.getType())) {
				found += item.getAmount();
			}
		}
		return found;
	}

	@EventHandler
	public void asd(final PlayerDeathEvent event) {
		event.setDeathMessage((String) null);
		final Player matou = event.getEntity().getKiller();
		final Player morreu = event.getEntity();
		if (event.getEntity() instanceof Player) {
			final int percentKillerXP = new Random().nextInt(12);
			final int percentEntityXP = new Random().nextInt(3);
			final PlayerManager jogador2 = new PlayerManager(morreu);
			new BukkitRunnable() {
				public void run() {
					event.getEntity().spigot().respawn();
				}
			}.runTaskLater(Main.getPlugin(), 1L);
			if (WarpsAPI.isInWarp(morreu, WarpsAPI.Warps.ONEVSONE)) {
				OnevsoneManager.fighting.remove(morreu.getUniqueId());
				OnevsoneManager.opponent.remove(morreu.getUniqueId());
				final int sopasKiller = this.itemsInInventory(matou.getInventory(),
						new Material[] { Material.MUSHROOM_SOUP });
				morreu.sendMessage("§c§lMORREU §fVoc\u00ea morreu para o jogador " + matou.getName()
						+ " e ele ficou com " + sopasKiller + " §fsopas restantes.");
				if (jogador2.getStatusGlobal().getXp() != 0) {
					if (percentEntityXP == 0) {
						jogador2.getStatusGlobal().removeXP(1);
						jogador2.getStatusGlobal().removeMoedas(12);
						morreu.sendMessage("§9§lXP §fVoc\u00ea perdeu §9§l1XP");
						morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l12MOEDAS");
					} else if (percentEntityXP == 3) {
						jogador2.getStatusGlobal().removeXP(3);
						jogador2.getStatusGlobal().removeMoedas(50);
						morreu.sendMessage("§9§lXP §fVoc\u00ea perdeu §9§l3XP");
						morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l50MOEDAS");
					} else if (percentEntityXP == 2) {
						jogador2.getStatusGlobal().removeXP(2);
						jogador2.getStatusGlobal().removeMoedas(25);
						morreu.sendMessage("§9§lXP §fVoc\u00ea perdeu §9§l2XP");
						morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l25MOEDAS");
					} else if (percentEntityXP == 1) {
						jogador2.getStatusGlobal().removeXP(1);
						jogador2.getStatusGlobal().removeMoedas(12);
						morreu.sendMessage("§9§lXP §fVoc\u00ea perdeu §9§l1XP");
						morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l12MOEDAS");
					}
				} else if (jogador2.getStatusGlobal().getMoedas() == 0) {
					jogador2.getStatusGlobal().removeMoedas(12);
					morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l12MOEDAS");
				}
			} else if (event.getEntity().getKiller() == null) {
				if (jogador2.getStatusGlobal().getXp() != 0) {
					if (percentEntityXP == 0) {
						jogador2.getStatusGlobal().removeXP(1);
						jogador2.getStatusGlobal().removeMoedas(12);
					} else if (percentEntityXP == 3) {
						jogador2.getStatusGlobal().removeXP(3);
						jogador2.getStatusGlobal().removeMoedas(50);
					} else if (percentEntityXP == 2) {
						jogador2.getStatusGlobal().removeXP(2);
						jogador2.getStatusGlobal().removeMoedas(25);
					} else if (percentEntityXP == 1) {
						jogador2.getStatusGlobal().removeXP(1);
						jogador2.getStatusGlobal().removeMoedas(12);
					}
				}
			} else {
				morreu.sendMessage("§c§lMORREU §fVoc\u00ea morreu para o jogador §c" + matou.getName() + "§f.");
				if (jogador2.getStatusGlobal().getXp() != 0) {
					if (percentEntityXP == 0) {
						jogador2.getStatusGlobal().removeXP(1);
						jogador2.getStatusGlobal().removeMoedas(12);
					} else if (percentEntityXP == 3) {
						jogador2.getStatusGlobal().removeXP(3);
						jogador2.getStatusGlobal().removeMoedas(50);
						morreu.sendMessage("§9§lXP §fVoc\u00ea perdeu §9§l3XP");
						morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l50MOEDAS");
					} else if (percentEntityXP == 2) {
						jogador2.getStatusGlobal().removeXP(2);
						jogador2.getStatusGlobal().removeMoedas(25);
						morreu.sendMessage("§9§lXP §fVoc\u00ea perdeu §9§l2XP");
						morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l25MOEDAS");
					} else if (percentEntityXP == 1) {
						jogador2.getStatusGlobal().removeXP(1);
						jogador2.getStatusGlobal().removeMoedas(12);
						morreu.sendMessage("§9§lXP §fVoc\u00ea perdeu §9§l1XP");
						morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l12MOEDAS");
					}
				} else if (jogador2.getStatusGlobal().getMoedas() == 0) {
					jogador2.getStatusGlobal().removeMoedas(12);
					morreu.sendMessage("§6§lMOEDAS §fVoc\u00ea perdeu §6§l12MOEDAS");
				}
			}
			final Clan clanDeaths = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(morreu);
			if (clanDeaths != null) {
				clanDeaths.getManager().addDeath(clanDeaths);
			}
			jogador2.getStatusKitPvP().addDeaths(1);
			jogador2.getStatusKitPvP().resetStreak();
			jogador2.getStatusKitPvP().save();
			jogador2.getStatusGlobal().save();
			if (event.getEntity().getKiller() instanceof Player) {
				final PlayerManager jogador3 = new PlayerManager(matou);
				if (WarpsAPI.isInWarp(matou, WarpsAPI.Warps.ONEVSONE)) {
					final int sopasEntity = this.itemsInInventory(morreu.getInventory(),
							new Material[] { Material.MUSHROOM_SOUP });
					matou.sendMessage("§a§lMATOU §fVoc\u00ea matou o jogador §a" + morreu.getName()
							+ " §fe ele ficou com §a" + sopasEntity + " §fsopas restantes.");
					if (percentKillerXP == 0) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(2);
							jogador3.getStatusGlobal().addMoedas(10);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(1);
							jogador3.getStatusGlobal().addMoedas(5);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
						}
					} else if (percentKillerXP == 12) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(24);
							jogador3.getStatusGlobal().addMoedas(120);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l24XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l120MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(12);
							jogador3.getStatusGlobal().addMoedas(60);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS");
						}
					} else if (percentKillerXP == 11) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(22);
							jogador3.getStatusGlobal().addMoedas(110);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l22XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l110MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(11);
							jogador3.getStatusGlobal().addMoedas(55);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l11XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l55MOEDAS");
						}
					} else if (percentKillerXP == 10) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(20);
							jogador3.getStatusGlobal().addMoedas(100);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l20XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l100MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(10);
							jogador3.getStatusGlobal().addMoedas(50);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS");
						}
					} else if (percentKillerXP == 9) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(18);
							jogador3.getStatusGlobal().addMoedas(70);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l18XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(9);
							jogador3.getStatusGlobal().addMoedas(45);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l9XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l45MOEDAS");
						}
					} else if (percentKillerXP == 8) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(16);
							jogador3.getStatusGlobal().addMoedas(80);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l16XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l80MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(8);
							jogador3.getStatusGlobal().addMoedas(40);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS");
						}
					} else if (percentKillerXP == 7) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(14);
							jogador3.getStatusGlobal().addMoedas(70);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l14XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(7);
							jogador3.getStatusGlobal().addMoedas(35);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l7XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l35MOEDAS");
						}
					} else if (percentKillerXP == 6) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(12);
							jogador3.getStatusGlobal().addMoedas(60);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(6);
							jogador3.getStatusGlobal().addMoedas(30);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS");
						}
					} else if (percentKillerXP == 5) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(10);
							jogador3.getStatusGlobal().addMoedas(50);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(5);
							jogador3.getStatusGlobal().addMoedas(25);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l5XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l25MOEDAS");
						}
					} else if (percentKillerXP == 4) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(8);
							jogador3.getStatusGlobal().addMoedas(40);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(4);
							jogador3.getStatusGlobal().addMoedas(20);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS");
						}
					} else if (percentKillerXP == 3) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(6);
							jogador3.getStatusGlobal().addMoedas(30);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(3);
							jogador3.getStatusGlobal().addMoedas(15);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l15MOEDAS");
						}
					} else if (percentKillerXP == 2) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(4);
							jogador3.getStatusGlobal().addMoedas(20);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(2);
							jogador3.getStatusGlobal().addMoedas(10);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS");
						}
					} else if (percentKillerXP == 1) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(2);
							jogador3.getStatusGlobal().addMoedas(10);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(1);
							jogador3.getStatusGlobal().addMoedas(5);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
						}
					}
					matou.setGameMode(GameMode.ADVENTURE);
					matou.setHealth(20.0);
					matou.getInventory().setArmorContents((ItemStack[]) null);
					matou.getActivePotionEffects().clear();
					OnevsoneManager.fighting.remove(matou.getUniqueId());
					OnevsoneManager.opponent.remove(matou.getUniqueId());
					Player[] onlinePlayers;
					for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
						final Player all = onlinePlayers[i];
						matou.showPlayer(all);
					}
					OnevsoneManager.sendItens(matou);
					ProtectionManager.setProtection(matou);
					WarpsAPI.setWarp(matou, WarpsAPI.Warps.ONEVSONE);
					WarpsAPI.goToWarp(matou, WarpsAPI.Warps.ONEVSONE);
				} else {
					matou.sendMessage("§a§lMATOU §fVoc\u00ea matou o jogador §a" + morreu.getName() + "§f.");
					if (percentKillerXP == 0) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(2);
							jogador3.getStatusGlobal().addMoedas(10);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(1);
							jogador3.getStatusGlobal().addMoedas(5);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
						}
					} else if (percentKillerXP == 12) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(24);
							jogador3.getStatusGlobal().addMoedas(120);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l24XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l120MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(12);
							jogador3.getStatusGlobal().addMoedas(60);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS");
						}
					} else if (percentKillerXP == 11) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(22);
							jogador3.getStatusGlobal().addMoedas(110);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l22XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l110MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(11);
							jogador3.getStatusGlobal().addMoedas(55);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l11XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l55MOEDAS");
						}
					} else if (percentKillerXP == 10) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(20);
							jogador3.getStatusGlobal().addMoedas(100);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l20XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l100MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(10);
							jogador3.getStatusGlobal().addMoedas(50);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS");
						}
					} else if (percentKillerXP == 9) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(18);
							jogador3.getStatusGlobal().addMoedas(70);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l18XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(9);
							jogador3.getStatusGlobal().addMoedas(45);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l9XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l45MOEDAS");
						}
					} else if (percentKillerXP == 8) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(16);
							jogador3.getStatusGlobal().addMoedas(80);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l16XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l80MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(8);
							jogador3.getStatusGlobal().addMoedas(40);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS");
						}
					} else if (percentKillerXP == 7) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(14);
							jogador3.getStatusGlobal().addMoedas(70);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l14XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(7);
							jogador3.getStatusGlobal().addMoedas(35);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l7XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l35MOEDAS");
						}
					} else if (percentKillerXP == 6) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(12);
							jogador3.getStatusGlobal().addMoedas(60);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(6);
							jogador3.getStatusGlobal().addMoedas(30);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS");
						}
					} else if (percentKillerXP == 5) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(10);
							jogador3.getStatusGlobal().addMoedas(50);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(5);
							jogador3.getStatusGlobal().addMoedas(25);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l5XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l25MOEDAS");
						}
					} else if (percentKillerXP == 4) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(8);
							jogador3.getStatusGlobal().addMoedas(40);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(4);
							jogador3.getStatusGlobal().addMoedas(20);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS");
						}
					} else if (percentKillerXP == 3) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(6);
							jogador3.getStatusGlobal().addMoedas(30);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(3);
							jogador3.getStatusGlobal().addMoedas(15);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l15MOEDAS");
						}
					} else if (percentKillerXP == 2) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(4);
							jogador3.getStatusGlobal().addMoedas(20);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(2);
							jogador3.getStatusGlobal().addMoedas(10);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS");
						}
					} else if (percentKillerXP == 1) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							jogador3.getStatusGlobal().addXP(2);
							jogador3.getStatusGlobal().addMoedas(10);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
						} else {
							jogador3.getStatusGlobal().addXP(1);
							jogador3.getStatusGlobal().addMoedas(5);
							matou.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
							matou.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
						}
					}
				}
				final Clan clanKiller = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(matou);
				if (clanKiller != null) {
					final int numberElo = new Random().nextInt(6);
					if (numberElo == 0) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							clanKiller.getManager().addElo(clanKiller, 2);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO §7(DoubleELO)");
						} else {
							clanKiller.getManager().addElo(clanKiller, 1);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l1ELO");
						}
					} else if (numberElo == 6) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							clanKiller.getManager().addElo(clanKiller, 12);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l12ELO §7(DoubleELO)");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l6ELO");
						}
					} else if (numberElo == 5) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							clanKiller.getManager().addElo(clanKiller, 10);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l10ELO §7(DoubleELO)");
						} else {
							clanKiller.getManager().addElo(clanKiller, 5);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l5ELO");
						}
					} else if (numberElo == 4) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							clanKiller.getManager().addElo(clanKiller, 8);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l8ELO §7(DoubleELO)");
						} else {
							clanKiller.getManager().addElo(clanKiller, 4);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l4ELO");
						}
					} else if (numberElo == 3) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							clanKiller.getManager().addElo(clanKiller, 6);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l6ELO §7(DoubleELO)");
						} else {
							clanKiller.getManager().addElo(clanKiller, 3);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l3ELO");
						}
					} else if (numberElo == 2) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							clanKiller.getManager().addElo(clanKiller, 4);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l4ELO §7(DoubleELO)");
						} else {
							clanKiller.getManager().addElo(clanKiller, 2);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO");
						}
					} else if (numberElo == 1) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(matou)) {
							clanKiller.getManager().addElo(clanKiller, 2);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO §7(DoubleELO)");
						} else {
							clanKiller.getManager().addElo(clanKiller, 1);
							matou.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l1ELO");
						}
					}
					clanKiller.getManager().addKill(clanKiller);
				}
				jogador3.getStatusKitPvP().addKills(1);
				jogador3.getStatusKitPvP().addStreak(1);
				jogador3.getStatusKitPvP().save();
				jogador3.getStatusGlobal().save();
				new BukkitRunnable() {
					public void run() {
						if (jogador3.getStatusKitPvP().getStreak() % 5 == 0) {
							Bukkit.broadcastMessage("§e§lKILLSTREAK §fO jogador " + matou.getName()
									+ " est\u00e1 com um §a§lSTREAK §fde " + Strings.primaryColor
									+ jogador3.getStatusKitPvP().getStreak());
						}
					}
				}.runTaskLater(Main.getPlugin(), 2L);
				Scoreboarding.updateKills(matou, matou.getScoreboard());
				Scoreboarding.updateModes(matou, matou.getScoreboard());
				Scoreboarding.updateXp(matou, matou.getScoreboard());
				Scoreboarding.updateStreak(matou, matou.getScoreboard());
			}
		}
	}

	@EventHandler
	public void playerQuitEvent(final PlayerQuitEvent event) {
		final Player player = event.getPlayer();
		if (OnevsoneManager.fighting.containsKey(player.getUniqueId())) {
			final Player killer = Bukkit.getPlayer(OnevsoneManager.fighting.get(player.getUniqueId()).getName());
			final int percentKillerXP = new Random().nextInt(6);
			final int percentEntityXP = new Random().nextInt(3);
			final PlayerManager jogador = new PlayerManager(killer);
			final PlayerManager jogador2 = new PlayerManager(player);
			if (jogador2.getStatusGlobal().getXp() != 0) {
				if (percentEntityXP == 0) {
					jogador2.getStatusGlobal().removeXP(1);
				} else if (percentEntityXP == 3) {
					jogador2.getStatusGlobal().removeXP(3);
				} else if (percentEntityXP == 2) {
					jogador2.getStatusGlobal().removeXP(2);
				} else if (percentEntityXP == 1) {
					jogador2.getStatusGlobal().removeXP(1);
				}
			}
			final Clan clanDeaths = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(player);
			if (clanDeaths != null) {
				clanDeaths.getManager().addDeath(clanDeaths);
			}
			jogador2.getStatusKitPvP().addDeaths(1);
			jogador2.getStatusKitPvP().resetStreak();
			jogador2.getStatusKitPvP().save();
			jogador2.getStatusGlobal().save();
			final int sopasEntity = this.itemsInInventory(player.getInventory(),
					new Material[] { Material.MUSHROOM_SOUP });
			if (WarpsAPI.isInWarp(killer, WarpsAPI.Warps.ONEVSONE)) {
				killer.sendMessage("§a§lMATOU §fVoc\u00ea matou o jogador §a" + player.getName()
						+ " §fe ele ficou com §a" + sopasEntity + " §fsopas restantes.");
				if (percentKillerXP == 0) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(2);
						jogador.getStatusGlobal().addMoedas(10);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(1);
						jogador.getStatusGlobal().addMoedas(5);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
					}
				} else if (percentKillerXP == 12) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(24);
						jogador.getStatusGlobal().addMoedas(120);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l24XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l120MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(12);
						jogador.getStatusGlobal().addMoedas(60);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS");
					}
				} else if (percentKillerXP == 11) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(22);
						jogador.getStatusGlobal().addMoedas(110);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l22XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l110MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(11);
						jogador.getStatusGlobal().addMoedas(55);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l11XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l55MOEDAS");
					}
				} else if (percentKillerXP == 10) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(20);
						jogador.getStatusGlobal().addMoedas(100);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l20XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l100MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(10);
						jogador.getStatusGlobal().addMoedas(50);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS");
					}
				} else if (percentKillerXP == 9) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(18);
						jogador.getStatusGlobal().addMoedas(70);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l18XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(9);
						jogador.getStatusGlobal().addMoedas(45);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l9XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l45MOEDAS");
					}
				} else if (percentKillerXP == 8) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(16);
						jogador.getStatusGlobal().addMoedas(80);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l16XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l80MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(8);
						jogador.getStatusGlobal().addMoedas(40);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS");
					}
				} else if (percentKillerXP == 7) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(14);
						jogador.getStatusGlobal().addMoedas(70);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l14XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(7);
						jogador.getStatusGlobal().addMoedas(35);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l7XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l35MOEDAS");
					}
				} else if (percentKillerXP == 6) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(12);
						jogador.getStatusGlobal().addMoedas(60);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(6);
						jogador.getStatusGlobal().addMoedas(30);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS");
					}
				} else if (percentKillerXP == 5) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(10);
						jogador.getStatusGlobal().addMoedas(50);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(5);
						jogador.getStatusGlobal().addMoedas(25);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l5XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l25MOEDAS");
					}
				} else if (percentKillerXP == 4) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(8);
						jogador.getStatusGlobal().addMoedas(40);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(4);
						jogador.getStatusGlobal().addMoedas(20);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS");
					}
				} else if (percentKillerXP == 3) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(6);
						jogador.getStatusGlobal().addMoedas(30);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(3);
						jogador.getStatusGlobal().addMoedas(15);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l15MOEDAS");
					}
				} else if (percentKillerXP == 2) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(4);
						jogador.getStatusGlobal().addMoedas(20);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(2);
						jogador.getStatusGlobal().addMoedas(10);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS");
					}
				} else if (percentKillerXP == 1) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						jogador.getStatusGlobal().addXP(2);
						jogador.getStatusGlobal().addMoedas(10);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
					} else {
						jogador.getStatusGlobal().addXP(1);
						jogador.getStatusGlobal().addMoedas(5);
						killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
						killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
					}
				}
			}
			final Clan clanKiller = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(killer);
			if (clanKiller != null) {
				final int numberElo = new Random().nextInt(6);
				if (numberElo == 0) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller.getManager().addElo(clanKiller, 2);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO §7(DoubleELO)");
					} else {
						clanKiller.getManager().addElo(clanKiller, 1);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l1ELO");
					}
				} else if (numberElo == 6) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller.getManager().addElo(clanKiller, 12);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l12ELO §7(DoubleELO)");
					} else {
						clanKiller.getManager().addElo(clanKiller, 6);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l6ELO");
					}
				} else if (numberElo == 5) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller.getManager().addElo(clanKiller, 10);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l10ELO §7(DoubleELO)");
					} else {
						clanKiller.getManager().addElo(clanKiller, 5);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l5ELO");
					}
				} else if (numberElo == 4) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller.getManager().addElo(clanKiller, 8);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l8ELO §7(DoubleELO)");
					} else {
						clanKiller.getManager().addElo(clanKiller, 4);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l4ELO");
					}
				} else if (numberElo == 3) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller.getManager().addElo(clanKiller, 6);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l6ELO §7(DoubleELO)");
					} else {
						clanKiller.getManager().addElo(clanKiller, 3);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l3ELO");
					}
				} else if (numberElo == 2) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller.getManager().addElo(clanKiller, 4);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l4ELO §7(DoubleELO)");
					} else {
						clanKiller.getManager().addElo(clanKiller, 2);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO");
					}
				} else if (numberElo == 1) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller.getManager().addElo(clanKiller, 2);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO §7(DoubleELO)");
					} else {
						clanKiller.getManager().addElo(clanKiller, 1);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l1ELO");
					}
				}
				clanKiller.getManager().addKill(clanKiller);
			}
			jogador.getStatusKitPvP().addKills(1);
			jogador.getStatusKitPvP().addStreak(1);
			jogador.getStatusKitPvP().save();
			jogador.getStatusGlobal().save();
			new BukkitRunnable() {
				public void run() {
					if (jogador.getStatusKitPvP().getStreak() % 5 == 0) {
						Bukkit.broadcastMessage(
								"§e§lKILLSTREAK §fO jogador " + killer.getName() + " est\u00e1 com um §a§lSTREAK §fde "
										+ Strings.primaryColor + jogador.getStatusKitPvP().getStreak());
					}
				}
			}.runTaskLater(Main.getPlugin(), 2L);
			killer.setGameMode(GameMode.ADVENTURE);
			killer.setHealth(20.0);
			killer.getInventory().setArmorContents((ItemStack[]) null);
			killer.getActivePotionEffects().clear();
			OnevsoneManager.fighting.remove(player.getUniqueId());
			OnevsoneManager.fighting.remove(killer.getUniqueId());
			OnevsoneManager.opponent.remove(player.getUniqueId());
			OnevsoneManager.opponent.remove(killer.getUniqueId());
			Player[] onlinePlayers;
			for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
				final Player all = onlinePlayers[i];
				killer.showPlayer(all);
			}
			OnevsoneManager.sendItens(killer);
			WarpsAPI.setWarp(killer, WarpsAPI.Warps.ONEVSONE);
			WarpsAPI.goToWarp(killer, WarpsAPI.Warps.ONEVSONE);
			Scoreboarding.updateKills(killer, killer.getScoreboard());
			Scoreboarding.updateModes(killer, killer.getScoreboard());
			Scoreboarding.updateXp(killer, killer.getScoreboard());
			Scoreboarding.updateStreak(killer, killer.getScoreboard());
			return;
		}
		if (Gladiator.fighting.containsKey(player.getName())) {
			final Player killer = Bukkit.getServer().getPlayer(Gladiator.fighting.get(player.getName()));
			final int percentKillerXP = new Random().nextInt(6);
			final int percentEntityXP = new Random().nextInt(3);
			final PlayerManager jogador = new PlayerManager(killer);
			final PlayerManager jogador2 = new PlayerManager(player);
			if (jogador2.getStatusGlobal().getXp() != 0) {
				if (percentEntityXP == 0) {
					jogador2.getStatusGlobal().removeXP(1);
				} else if (percentEntityXP == 3) {
					jogador2.getStatusGlobal().removeXP(3);
				} else if (percentEntityXP == 2) {
					jogador2.getStatusGlobal().removeXP(2);
				} else if (percentEntityXP == 1) {
					jogador2.getStatusGlobal().removeXP(1);
				}
			}
			jogador2.getStatusKitPvP().addDeaths(1);
			jogador2.getStatusKitPvP().resetStreak();
			jogador2.getStatusKitPvP().save();
			jogador2.getStatusGlobal().save();
			final Clan clanDeaths = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(player);
			if (clanDeaths != null) {
				clanDeaths.getManager().addDeath(clanDeaths);
			}
			killer.sendMessage("§a§lMATOU §fVoc\u00ea matou o jogador §a" + player.getName() + "§f.");
			if (percentKillerXP == 0) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(2);
					jogador.getStatusGlobal().addMoedas(10);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(1);
					jogador.getStatusGlobal().addMoedas(5);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
				}
			} else if (percentKillerXP == 12) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(24);
					jogador.getStatusGlobal().addMoedas(120);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l24XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l120MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(12);
					jogador.getStatusGlobal().addMoedas(60);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS");
				}
			} else if (percentKillerXP == 11) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(22);
					jogador.getStatusGlobal().addMoedas(110);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l22XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l110MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(11);
					jogador.getStatusGlobal().addMoedas(55);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l11XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l55MOEDAS");
				}
			} else if (percentKillerXP == 10) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(20);
					jogador.getStatusGlobal().addMoedas(100);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l20XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l100MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(10);
					jogador.getStatusGlobal().addMoedas(50);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS");
				}
			} else if (percentKillerXP == 9) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(18);
					jogador.getStatusGlobal().addMoedas(70);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l18XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(9);
					jogador.getStatusGlobal().addMoedas(45);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l9XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l45MOEDAS");
				}
			} else if (percentKillerXP == 8) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(16);
					jogador.getStatusGlobal().addMoedas(80);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l16XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l80MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(8);
					jogador.getStatusGlobal().addMoedas(40);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS");
				}
			} else if (percentKillerXP == 7) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(14);
					jogador.getStatusGlobal().addMoedas(70);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l14XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l70MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(7);
					jogador.getStatusGlobal().addMoedas(35);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l7XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l35MOEDAS");
				}
			} else if (percentKillerXP == 6) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(12);
					jogador.getStatusGlobal().addMoedas(60);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l12XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l60MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(6);
					jogador.getStatusGlobal().addMoedas(30);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS");
				}
			} else if (percentKillerXP == 5) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(10);
					jogador.getStatusGlobal().addMoedas(50);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l10XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l50MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(5);
					jogador.getStatusGlobal().addMoedas(25);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l5XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l25MOEDAS");
				}
			} else if (percentKillerXP == 4) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(8);
					jogador.getStatusGlobal().addMoedas(40);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l8XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l40MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(4);
					jogador.getStatusGlobal().addMoedas(20);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS");
				}
			} else if (percentKillerXP == 3) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(6);
					jogador.getStatusGlobal().addMoedas(30);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l6XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l30MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(3);
					jogador.getStatusGlobal().addMoedas(15);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l15MOEDAS");
				}
			} else if (percentKillerXP == 2) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(4);
					jogador.getStatusGlobal().addMoedas(20);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l4XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l20MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(2);
					jogador.getStatusGlobal().addMoedas(10);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS");
				}
			} else if (percentKillerXP == 1) {
				if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
					jogador.getStatusGlobal().addXP(2);
					jogador.getStatusGlobal().addMoedas(10);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l2XP §7(DoubleXP)");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l10MOEDAS §7(DoubleMoedas)");
				} else {
					jogador.getStatusGlobal().addXP(1);
					jogador.getStatusGlobal().addMoedas(5);
					killer.sendMessage("§9§lXP §fVoc\u00ea ganhou §9§l1XP");
					killer.sendMessage("§6§lMOEDAS §fVoc\u00ea ganhou §6§l5MOEDAS");
				}
			}
			final Clan clanKiller2 = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(killer);
			if (clanKiller2 != null) {
				final int numberElo2 = new Random().nextInt(6);
				if (numberElo2 == 0) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller2.getManager().addElo(clanKiller2, 2);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO §7(DoubleELO)");
					} else {
						clanKiller2.getManager().addElo(clanKiller2, 1);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l1ELO");
					}
				} else if (numberElo2 == 6) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller2.getManager().addElo(clanKiller2, 12);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l12ELO §7(DoubleELO)");
					} else {
						clanKiller2.getManager().addElo(clanKiller2, 6);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l6ELO");
					}
				} else if (numberElo2 == 5) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller2.getManager().addElo(clanKiller2, 10);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l10ELO §7(DoubleELO)");
					} else {
						clanKiller2.getManager().addElo(clanKiller2, 5);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l5ELO");
					}
				} else if (numberElo2 == 4) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller2.getManager().addElo(clanKiller2, 8);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l8ELO §7(DoubleELO)");
					} else {
						clanKiller2.getManager().addElo(clanKiller2, 4);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l4ELO");
					}
				} else if (numberElo2 == 3) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller2.getManager().addElo(clanKiller2, 6);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l6ELO §7(DoubleELO)");
					} else {
						clanKiller2.getManager().addElo(clanKiller2, 3);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l3ELO");
					}
				} else if (numberElo2 == 2) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller2.getManager().addElo(clanKiller2, 4);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l4ELO §7(DoubleELO)");
					} else {
						clanKiller2.getManager().addElo(clanKiller2, 2);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO");
					}
				} else if (numberElo2 == 1) {
					if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
						clanKiller2.getManager().addElo(clanKiller2, 2);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l2ELO §7(DoubleELO)");
					} else {
						clanKiller2.getManager().addElo(clanKiller2, 1);
						killer.sendMessage("§3§lELO §fVoc\u00ea ganhou §3§l1ELO");
					}
				}
				clanKiller2.getManager().addKill(clanKiller2);
			}
			jogador.getStatusKitPvP().addKills(1);
			jogador.getStatusKitPvP().addStreak(1);
			jogador.getStatusKitPvP().save();
			jogador.getStatusGlobal().save();
			new BukkitRunnable() {
				public void run() {
					if (jogador.getStatusKitPvP().getStreak() % 5 == 0) {
						Bukkit.broadcastMessage(
								"§e§lKILLSTREAK §fO jogador " + killer.getName() + " est\u00e1 com um §a§lSTREAK §fde "
										+ Strings.primaryColor + jogador.getStatusKitPvP().getStreak());
					}
				}
			}.runTaskLater(Main.getPlugin(), 2L);
			Scoreboarding.updateKills(killer, killer.getScoreboard());
			Scoreboarding.updateModes(killer, killer.getScoreboard());
			Scoreboarding.updateXp(killer, killer.getScoreboard());
			Scoreboarding.updateStreak(killer, killer.getScoreboard());
		}
	}

	@EventHandler
	public void asd(final PlayerRespawnEvent event) {
		final Player player = event.getPlayer();
		new BukkitRunnable() {
			public void run() {
				if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.FPS)) {
					WarpManager.send(player, WarpsAPI.Warps.FPS);
				} else if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.ONEVSONE)) {
					WarpManager.send(player, WarpsAPI.Warps.ONEVSONE);
				} else if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.ARENA)) {
					WarpManager.send(player, WarpsAPI.Warps.SPAWN);
				}
			}
		}.runTaskLater(Main.getPlugin(), 10L);
	}
}
