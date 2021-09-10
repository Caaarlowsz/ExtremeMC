package extrememc.kitpvp.api.hability.kits;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import extrememc.kitpvp.api.CooldownAPI;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;

public class Thor implements Listener {
	@EventHandler
	public void ThorKit(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (p.getItemInHand().getType() == Material.WOOD_AXE && e.getAction() == Action.RIGHT_CLICK_BLOCK
				&& KitAPI.hasKit(p, Kits.THOR)) {
			if (CooldownAPI.cooldown.containsKey(p.getName())) {
				if (CooldownAPI.getCooldown(p) > 0L) {
					p.sendMessage("§c§lCOOLDOWN §fAguarde §c" + CooldownAPI.getCooldown(p)
							+ " §fsegundos para utilizar o kit!");
					return;
				}
				CooldownAPI.removeCooldown(p);
				CooldownAPI.addCooldown(p, 5);
				final Location loc = p.getTargetBlock(null, 70).getLocation();
				p.getWorld().strikeLightning(loc);
				e.setCancelled(true);
				p.damage(0.0);
			} else {
				CooldownAPI.removeCooldown(p);
				CooldownAPI.addCooldown(p, 5);
				final Location loc = p.getTargetBlock(null, 70).getLocation();
				p.getWorld().strikeLightning(loc);
				e.setCancelled(true);
				p.damage(0.0);
			}
		}
	}
}
