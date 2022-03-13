package extrememc.kitpvp.api.hability.kits;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;

public class Anchor implements Listener {
	@EventHandler
	public void EntityDamageByEntityEvent(final EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		final Player p = (Player) e.getEntity();
		final Player a = (Player) e.getDamager();
		if (KitAPI.hasKit(p, Kits.ANCHOR)) {
			p.setVelocity(new Vector());
			p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			a.playSound(a.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ExtremePvP.getInstance(), new Runnable() {
				@Override
				public void run() {
					p.setVelocity(new Vector());
				}
			}, 1L);
		}
		if (KitAPI.hasKit(a, Kits.ANCHOR)) {
			a.playSound(a.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			p.setVelocity(new Vector());
			p.playSound(a.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(ExtremePvP.getInstance(), new Runnable() {
				@Override
				public void run() {
					p.setVelocity(new Vector());
				}
			}, 1L);
		}
	}
}
