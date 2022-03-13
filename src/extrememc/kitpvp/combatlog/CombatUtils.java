package extrememc.kitpvp.combatlog;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import extrememc.common.clanManager.Clan;
import extrememc.common.playerManager.PlayerManager;
import extrememc.common.strings.Strings;
import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;
import extrememc.kitpvp.api.hability.kits.Gladiator;
import extrememc.kitpvp.api.warp.WarpsAPI;
import extrememc.kitpvp.protectionManager.ProtectionManager;
import extrememc.kitpvp.scoreboard.Scoreboarding;

public class CombatUtils implements Listener {
	public static HashMap<Player, Player> combat;

	static {
		CombatUtils.combat = new HashMap<Player, Player>();
	}

	public static boolean inCombat(final Player player) {
		return CombatUtils.combat.containsKey(player);
	}

	public static void setCombat(final Player player, final Player hitted) {
		CombatUtils.combat.put(player, hitted);
		CombatUtils.combat.put(hitted, player);
	}

	public static void removeCombat(final Player player) {
		final Player hitted = CombatUtils.combat.get(player);
		CombatUtils.combat.remove(player);
		CombatUtils.combat.remove(hitted);
	}

	@EventHandler
	public void entityDamageByEntity(final EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) {
			return;
		}
		final Player hitter = (Player) event.getDamager();
		final Player hitted = (Player) event.getEntity();
		if (WarpsAPI.isInWarp(hitted, WarpsAPI.Warps.ONEVSONE) || WarpsAPI.isInWarp(hitter, WarpsAPI.Warps.ONEVSONE)
				|| ProtectionManager.hasProtection(hitted) || ProtectionManager.hasProtection(hitter)) {
			return;
		}
		if (!inCombat(hitted) && !inCombat(hitter)) {
			setCombat(hitter, hitted);
			Bukkit.getScheduler().scheduleSyncDelayedTask(ExtremePvP.getPlugin(), new Runnable() {
				@Override
				public void run() {
					if (CombatUtils.inCombat(hitted) || CombatUtils.inCombat(hitter)) {
						CombatUtils.removeCombat(hitter);
					}
				}
			}, 200L);
		}
	}

	@EventHandler
	public void playerQuitEvent(final PlayerQuitEvent event) {
		if (inCombat(event.getPlayer())) {
			final Player player = event.getPlayer();
			final Player inCombat = CombatUtils.combat.get(player);
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
				if (jogador2.getStatusKitPvP().getStreak() % 5 == 0) {
					Bukkit.broadcastMessage("�c�lKILLSTREAK �fO jogador " + player.getName()
							+ " perdeu seu �4�lSTREAK �fde " + Strings.primaryColor
							+ jogador2.getStatusKitPvP().getStreak() + " �fpara " + killer.getName());
				}
				jogador2.getStatusKitPvP().addDeaths(1);
				jogador2.getStatusKitPvP().resetStreak();
				jogador2.getStatusKitPvP().save();
				jogador2.getStatusGlobal().save();
				final Clan clanDeaths = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(player);
				if (clanDeaths != null) {
					clanDeaths.getManager().addDeath(clanDeaths);
				}
				if (percentKillerXP == 12) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(24);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +24 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(12);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +12 XP");
					}
				} else if (percentKillerXP == 11) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(22);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +22 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(11);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +11 XP");
					}
				} else if (percentKillerXP == 10) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(20);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +20 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(10);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +10 XP");
					}
				} else if (percentKillerXP == 9) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(18);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +18 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(9);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +9 XP");
					}
				} else if (percentKillerXP == 8) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(16);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +16 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(8);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +8 XP");
					}
				} else if (percentKillerXP == 7) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(14);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +14 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(7);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +7 XP");
					}
				} else if (percentKillerXP == 6) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(12);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +12 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(6);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +6 XP");
					}
				} else if (percentKillerXP == 5) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(10);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +10 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(5);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +5 XP");
					}
				} else if (percentKillerXP == 4) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(8);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +8 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(4);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +4 XP");
					}
				} else if (percentKillerXP == 3) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(6);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +6 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(3);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +3 XP");
					}
				} else if (percentKillerXP == 2) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(4);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +24 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(2);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +2 XP");
					}
				} else if (percentKillerXP == 1) {
					if (extrememc.common.Main.getInstance().getPermissions().isLight(killer)) {
						jogador.getStatusGlobal().addXP(2);
						killer.sendMessage(
								"�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +2 DoubleXP");
					} else {
						jogador.getStatusGlobal().addXP(1);
						killer.sendMessage("�a�lMATOU �fVoc\u00ea matou o jogador �a" + player.getName() + "�f. +1 XP");
					}
				}
				final Clan clanKiller = extrememc.common.Main.getInstance().getClanManager().getPlayerClan(killer);
				if (clanKiller != null) {
					final int numberElo = new Random().nextInt(6);
					if (numberElo == 0) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
							clanKiller.getManager().addElo(clanKiller, 2);
							killer.sendMessage("�a�lCLAN �f+2 DoubleElo");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+1 Elos");
						}
					} else if (numberElo == 6) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
							clanKiller.getManager().addElo(clanKiller, 12);
							killer.sendMessage("�a�lCLAN �f+12 DoubleElo");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+6 Elos");
						}
					} else if (numberElo == 5) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
							clanKiller.getManager().addElo(clanKiller, 10);
							killer.sendMessage("�a�lCLAN �f+10 DoubleElo");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+5 Elos");
						}
					} else if (numberElo == 4) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
							clanKiller.getManager().addElo(clanKiller, 8);
							killer.sendMessage("�a�lCLAN �f+8 DoubleElo");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+4 Elos");
						}
					} else if (numberElo == 3) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+6 DoubleElo");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+3 Elos");
						}
					} else if (numberElo == 2) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
							clanKiller.getManager().addElo(clanKiller, 4);
							killer.sendMessage("�a�lCLAN �f+4 DoubleElo");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+2 Elos");
						}
					} else if (numberElo == 1) {
						if (extrememc.common.Main.getInstance().getPermissions().isBeta(killer)) {
							clanKiller.getManager().addElo(clanKiller, 2);
							killer.sendMessage("�a�lCLAN �f+2 DoubleElo");
						} else {
							clanKiller.getManager().addElo(clanKiller, 6);
							killer.sendMessage("�a�lCLAN �f+1 Elos");
						}
					}
					clanKiller.getManager().addKill(clanKiller);
				}
				jogador.getStatusKitPvP().addKills(1);
				jogador.getStatusKitPvP().addStreak(1);
				if (jogador.getStatusKitPvP().getStreak() > jogador.getStatusKitPvP().getMaxStreak()) {
					jogador.getStatusKitPvP().setMaxStreak(jogador.getStatusKitPvP().getStreak());
					Bukkit.broadcastMessage("�e�lMAXSTREAK �fO jogador " + killer.getName()
							+ " est\u00e1 com o maior �a�lSTREAK �fdo servidor.");
				}
				jogador.getStatusKitPvP().save();
				jogador.getStatusGlobal().save();
				new BukkitRunnable() {
					public void run() {
						if (jogador.getStatusKitPvP().getStreak() % 5 == 0) {
							Bukkit.broadcastMessage("�e�lKILLSTREAK �fO jogador " + killer.getName()
									+ " est\u00e1 com um �a�lSTREAK �fde " + Strings.primaryColor
									+ jogador.getStatusKitPvP().getStreak());
						}
					}
				}.runTaskLater(ExtremePvP.getPlugin(), 2L);
				CombatUtils.combat.remove(player);
				CombatUtils.combat.remove(inCombat);
				Scoreboarding.updateKills(inCombat, inCombat.getScoreboard());
				Scoreboarding.updateStreak(inCombat, inCombat.getScoreboard());
			}
		}
	}

	@EventHandler
	public void command(final PlayerCommandPreprocessEvent event) {
		if (inCombat(event.getPlayer())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("�c�lCOMBATE �fEsse comando esta bloqueado em combate!");
		}
	}

	@EventHandler
	public void asd(final PlayerDeathEvent event) {
		event.setDeathMessage((String) null);
		final Player matou = event.getEntity().getKiller();
		final Player morreu = event.getEntity();
		if (inCombat(morreu) || inCombat(matou)) {
			removeCombat(matou);
		}
	}
}
