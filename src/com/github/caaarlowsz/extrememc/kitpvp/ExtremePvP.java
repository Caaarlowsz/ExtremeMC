package com.github.caaarlowsz.extrememc.kitpvp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.caaarlowsz.kitpvpapi.KitPvP;
import com.github.caaarlowsz.kitpvpapi.KitPvPAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import extrememc.kitpvp.api.hability.kits.Gladiator;
import extrememc.kitpvp.automatic.ListenerManager;
import extrememc.kitpvp.automatic.TeleportServer;
import extrememc.kitpvp.commands.AdminCommand;
import extrememc.kitpvp.commands.BuildCommand;
import extrememc.kitpvp.commands.KitsCommand;
import extrememc.kitpvp.commands.SetwarpCommand;
import extrememc.kitpvp.commands.SpawnCommand;
import extrememc.kitpvp.scoreboard.Scoreboarding;

public class ExtremePvP extends JavaPlugin implements KitPvP {

	@Override
	public void onEnable() {
		super.onEnable();
		KitPvPAPI.setInstance(this);

		// TODO: Remover quando melhorar a classe principal
		this.enable();
	}

	@Override
	public void onDisable() {
		super.onDisable();

		// TODO: Remover quando melhorar a classe principal
		this.disable();
	}

	// TODO: Melhorar a classe principal

	public static ArrayList<Player> checkBelowName;
	public static ExtremePvP instance;
	public static Plugin plugin;
	public static boolean fullkit;
	public static ArrayList<UUID> scoreboard;

	static {
		ExtremePvP.checkBelowName = new ArrayList<Player>();
		ExtremePvP.scoreboard = new ArrayList<UUID>();
	}

	public static ExtremePvP getInstance() {
		return ExtremePvP.instance;
	}

	public static Plugin getPlugin() {
		return ExtremePvP.plugin;
	}

	public void enable() {
		ExtremePvP.instance = this;
		ExtremePvP.plugin = this;
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
			final Player todos = onlinePlayers[i];
			TeleportServer.sendTeleport(todos, TeleportServer.Servers.LOBBY);
		}
		for (final World dia : Bukkit.getServer().getWorlds()) {
			dia.setGameRuleValue("doDaylightCycle", "false");
		}
		Player[] onlinePlayers2;
		for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
			final Player player = onlinePlayers2[j];
			for (final Entity entity : player.getWorld().getEntities()) {
				if (entity instanceof Item) {
					entity.remove();
				}
			}
		}
		this.saveDefaultConfig();
		ListenerManager.loadListener();
		this.loadCommand();
		Scoreboarding.updateScore();
		ExtremePvP.fullkit = false;
	}

	public void loadCommand() {
		this.registerCommand(new AdminCommand());
		this.registerCommand(new KitsCommand());
		this.registerCommand(new BuildCommand());
		this.registerCommand(new SpawnCommand());
		this.registerCommand(new SetwarpCommand());
	}

	public void registerCommand(final CommandExecutor command) {
		final String name = command.getClass().getSimpleName().split("Command")[0].toLowerCase();
		this.getCommand(name).setExecutor(command);
	}

	public void disable() {
		HandlerList.unregisterAll();
		ExtremePvP.instance = null;
		ExtremePvP.plugin = null;
		Player[] onlinePlayers;
		for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
			final Player todos = onlinePlayers[i];
			if (Gladiator.fighting.containsKey(todos.getName())) {
				final Location loc = Gladiator.localizacao.get(todos);
				final List<Location> cuboid = new ArrayList<Location>();
				cuboid.clear();
				for (int bX = -10; bX <= 10; ++bX) {
					for (int bZ = -10; bZ <= 10; ++bZ) {
						for (int bY = -1; bY <= 10; ++bY) {
							if (bY == 10) {
								cuboid.add(loc.clone().add(bX, bY, bZ));
							} else if (bY == -1) {
								cuboid.add(loc.clone().add(bX, bY, bZ));
							} else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
								cuboid.add(loc.clone().add(bX, bY, bZ));
							}
						}
					}
				}
				for (final Location loc2 : cuboid) {
					loc2.getBlock().setType(Material.AIR);
					if (Gladiator.bloco.containsKey(loc2)) {
						Gladiator.bloco.get(loc2).setType(Material.AIR);
					}
				}
			}
		}
	}

	public List<Player> getOnlinePlayers() {
		final List<Player> list = new ArrayList<Player>();
		for (final World world : getInstance().getServer().getWorlds()) {
			for (final Player player : world.getPlayers()) {
				list.add(player);
			}
		}
		return list;
	}
}
