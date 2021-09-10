package extrememc.kitpvp.api.hability.kits;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;

public class Fisherman implements Listener {
	@EventHandler
	public void asd(final PlayerFishEvent e) {
		final Player p = e.getPlayer();
		final Entity caught = e.getCaught();
		final Block block = e.getHook().getLocation().getBlock();
		if (caught != null && caught != block && KitAPI.hasKit(p, Kits.FISHERMAN)) {
			caught.teleport(p.getPlayer().getLocation());
		}
	}
}
