package extrememc.kitpvp.api.jump;

import java.util.ArrayList;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;
import extrememc.kitpvp.api.warp.WarpsAPI;

public class Launchers implements Listener {
	public static ArrayList<String> esponja;

	static {
		Launchers.esponja = new ArrayList<String>();
	}

	@EventHandler
	public void asd(final PlayerMoveEvent e) {
		final Player player = e.getPlayer();
		if (e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
			Launchers.esponja.remove(player.getName());
			final Location loc = e.getTo().getBlock().getLocation();
			final Vector sponge = player.getLocation().getDirection().multiply(0).setY(6);
			player.setVelocity(sponge);
			if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.ARENA)) {
				for (final Player players : ExtremePvP.getInstance().getOnlinePlayers()) {
					players.playSound(loc, Sound.HORSE_JUMP, 6.0f, 1.0f);
					players.playEffect(loc, Effect.ENDER_SIGNAL, (Object) null);
				}
			}
			Launchers.esponja.add(player.getName());
		}
	}

	@EventHandler
	public void asd(final EntityDamageEvent e) {
		final Player p = (Player) e.getEntity();
		if (e.getEntity() instanceof Player && e.getCause().equals(EntityDamageEvent.DamageCause.FALL)
				&& Launchers.esponja.contains(p.getName())) {
			e.setCancelled(true);
			Launchers.esponja.remove(p.getName());
		}
	}
}
