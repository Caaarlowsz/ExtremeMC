package extrememc.kitpvp.api.hability.kits;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;

public class Stomper implements Listener {
	public void onPlayerStomp(final EntityDamageEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
			return;
		}
		if (KitAPI.hasKit(p, Kits.STOMPER)) {
			for (final Entity ent : p.getNearbyEntities(5.0, 3.0, 5.0)) {
				if (!(ent instanceof Player)) {
					continue;
				}
				final Player plr = (Player) ent;
				if (e.getDamage() <= 4.0) {
					e.setCancelled(true);
					return;
				}
				if (plr.isSneaking()) {
					continue;
				}
				if (KitAPI.hasKit(plr, Kits.ANTISTOMPER)) {
					continue;
				}
				plr.damage(e.getDamage(), p);
				plr.getKiller();
			}
			e.setDamage(4.0);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	private void onPlayerFallStomper(final EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		final Player player = (Player) e.getEntity();
		if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
			return;
		}
		if (KitAPI.hasKit(player, Kits.STOMPER)) {
			for (final Entity entity : player.getNearbyEntities(6.0, 3.0, 6.0)) {
				if (entity instanceof Player) {
					final Player players = (Player) entity;
					if (players == player) {
						continue;
					}
					if (players.isSneaking() || KitAPI.hasKit(players, Kits.ANTISTOMPER)) {
						players.damage(0.1, player);
						players.damage(3.9);
					} else {
						players.damage(0.1, player);
						players.damage(player.getFallDistance() - 8.1f);
					}
				}
			}
			final Location player_location = player.getLocation();
			final int radius = 4;
			for (int i = 0; i < 6; ++i) {
				for (double x = -radius; x <= radius; ++x) {
					for (double z = -radius; z <= radius; ++z) {
						final Location effect_location = new Location(player_location.getWorld(),
								player_location.getX() + x, player_location.getY(), player_location.getZ() + z);
						effect_location.getWorld().playEffect(effect_location, Effect.WITCH_MAGIC, 500);
					}
				}
			}
			if (e.getDamage() > 4.0) {
				e.setDamage(4.0);
			}
		}
	}
}
