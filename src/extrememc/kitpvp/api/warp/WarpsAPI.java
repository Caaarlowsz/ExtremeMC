package extrememc.kitpvp.api.warp;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;

public class WarpsAPI implements Listener {
	public static WarpsAPI pasta;
	public static File fWarps;
	public static FileConfiguration kWarps;
	public static HashMap<Player, Warps> Warp;
	public static HashMap<Player, Locais> Local;

	static {
		WarpsAPI.pasta = new WarpsAPI();
		WarpsAPI.Warp = new HashMap<Player, Warps>();
		WarpsAPI.Local = new HashMap<Player, Locais>();
	}

	public static WarpsAPI getConfig() {
		return WarpsAPI.pasta;
	}

	public WarpsAPI() {
		final Plugin plugin = ExtremePvP.getPlugin();
		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		WarpsAPI.fWarps = new File(plugin.getDataFolder(), "warps.yml");
		if (WarpsAPI.fWarps.exists()) {
			try {
				WarpsAPI.fWarps.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		WarpsAPI.kWarps = YamlConfiguration.loadConfiguration(WarpsAPI.fWarps);
	}

	public FileConfiguration config() {
		return WarpsAPI.kWarps;
	}

	public static void setWarp(final Player p, final Warps warp) {
		WarpsAPI.Warp.put(p, warp);
	}

	public static Warps getPlayerWarp(final Player p) {
		return WarpsAPI.Warp.get(p);
	}

	public static String getWarp(final Player p) {
		if (isInWarp(p, Warps.ARENA)) {
			return "Arena";
		}
		if (isInWarp(p, Warps.FPS)) {
			return "FPS";
		}
		if (isInWarp(p, Warps.SPAWN)) {
			return "Spawn";
		}
		return "Spawn";
	}

	public static boolean isInWarp(final Player p, final Warps warp) {
		final Warps playerwarp = WarpsAPI.Warp.get(p);
		return warp.equals(playerwarp);
	}

	public static boolean isInWarp(final Player p, final Locais local) {
		final Locais playerwarp = WarpsAPI.Local.get(p);
		return local.equals(playerwarp);
	}

	public static void setWarpLocation(final Player p, final Warps warp) {
		WarpsAPI.kWarps.set("Warps." + warp.toString().toUpperCase() + ".X", p.getLocation().getX());
		WarpsAPI.kWarps.set("Warps." + warp.toString().toUpperCase() + ".Y", p.getLocation().getY());
		WarpsAPI.kWarps.set("Warps." + warp.toString().toUpperCase() + ".Z", p.getLocation().getZ());
		WarpsAPI.kWarps.set("Warps." + warp.toString().toUpperCase() + ".Pitch", p.getLocation().getPitch());
		WarpsAPI.kWarps.set("Warps." + warp.toString().toUpperCase() + ".Yaw", p.getLocation().getYaw());
		WarpsAPI.kWarps.set("Warps." + warp.toString().toUpperCase() + ".World", p.getLocation().getWorld().getName());
		save();
	}

	public static void setWarpLocation(final Player p, final Locais local) {
		WarpsAPI.kWarps.set("Warps." + local.toString().toUpperCase() + ".X", p.getLocation().getX());
		WarpsAPI.kWarps.set("Warps." + local.toString().toUpperCase() + ".Y", p.getLocation().getY());
		WarpsAPI.kWarps.set("Warps." + local.toString().toUpperCase() + ".Z", p.getLocation().getZ());
		WarpsAPI.kWarps.set("Warps." + local.toString().toUpperCase() + ".Pitch", p.getLocation().getPitch());
		WarpsAPI.kWarps.set("Warps." + local.toString().toUpperCase() + ".Yaw", p.getLocation().getYaw());
		WarpsAPI.kWarps.set("Warps." + local.toString().toUpperCase() + ".World", p.getLocation().getWorld().getName());
		save();
	}

	public static void goToWarp(final Player p, final Warps warp) {
		if (WarpsAPI.kWarps.contains("Warps." + warp.toString().toUpperCase())) {
			final double X = WarpsAPI.kWarps.getDouble("Warps." + warp.toString().toUpperCase() + ".X");
			final double Y = WarpsAPI.kWarps.getDouble("Warps." + warp.toString().toUpperCase() + ".Y");
			final double Z = WarpsAPI.kWarps.getDouble("Warps." + warp.toString().toUpperCase() + ".Z");
			final float Pitch = (float) WarpsAPI.kWarps.getDouble("Warps." + warp.toString().toUpperCase() + ".Pitch");
			final float Yaw = (float) WarpsAPI.kWarps.getDouble("Warps." + warp.toString().toUpperCase() + ".Yaw");
			final World World = Bukkit
					.getWorld(WarpsAPI.kWarps.getString("Warps." + warp.toString().toUpperCase() + ".World"));
			final Location loc = new Location(World, X, Y, Z, Yaw, Pitch);
			p.teleport(loc);
		} else {
			p.sendMessage(
					"�3�lWARP �fA warp �c" + warp.name().toUpperCase() + " �fest\u00e1 em �c�lMANUTEN\u00c7\u00c3O�f.");
		}
	}

	public static void goToWarp(final Player p, final Locais local) {
		if (WarpsAPI.kWarps.contains("Warps." + local.toString().toUpperCase())) {
			final double X = WarpsAPI.kWarps.getDouble("Warps." + local.toString().toUpperCase() + ".X");
			final double Y = WarpsAPI.kWarps.getDouble("Warps." + local.toString().toUpperCase() + ".Y");
			final double Z = WarpsAPI.kWarps.getDouble("Warps." + local.toString().toUpperCase() + ".Z");
			final float Pitch = (float) WarpsAPI.kWarps.getDouble("Warps." + local.toString().toUpperCase() + ".Pitch");
			final float Yaw = (float) WarpsAPI.kWarps.getDouble("Warps." + local.toString().toUpperCase() + ".Yaw");
			final World World = Bukkit
					.getWorld(WarpsAPI.kWarps.getString("Warps." + local.toString().toUpperCase() + ".World"));
			final Location loc = new Location(World, X, Y, Z, Yaw, Pitch);
			p.teleport(loc);
		} else {
			p.sendMessage("�3�lWARP �fO local �c" + local.name().toUpperCase() + " �cainda n\u00e3o foi �a�lSETADO�f.");
		}
	}

	public static Double getInConfigLocationDoubleX(final String Warp) {
		return getConfig().config().getDouble("Warps." + Warp.toUpperCase() + ".X");
	}

	public static int getInConfigLocationIntX(final String Warp) {
		return getConfig().config().getInt("Warps." + Warp.toUpperCase() + ".X");
	}

	public static int getInConfigLocationIntY(final String Warp) {
		return getConfig().config().getInt("Warps." + Warp.toUpperCase() + ".Y");
	}

	public static int getInConfigLocationIntZ(final String Warp) {
		return getConfig().config().getInt("Warps." + Warp.toUpperCase() + ".Z");
	}

	public static void save() {
		try {
			WarpsAPI.kWarps.save(WarpsAPI.fWarps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public enum Locais {
		ONEVSONEPOS1("ONEVSONEPOS1", 0), ONEVSONEPOS2("ONEVSONEPOS2", 1);

		Locais(final String s, final int n) {
		}
	}

	public enum Warps {
		SPAWN("SPAWN", 0), ARENA("ARENA", 1), FPS("FPS", 2), ONEVSONE("ONEVSONE", 3), SCREENSHARE("SCREENSHARE", 4),
		EVENTO("EVENTO", 5);

		Warps(final String s, final int n) {
		}
	}
}
