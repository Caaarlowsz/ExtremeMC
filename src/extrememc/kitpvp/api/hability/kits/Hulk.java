package extrememc.kitpvp.api.hability.kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import extrememc.kitpvp.api.CooldownAPI;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;

public class Hulk implements Listener {
	@EventHandler
	public void hulk(final PlayerInteractEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		final Player p = event.getPlayer();
		final Player r = (Player) event.getRightClicked();
		if (p.getItemInHand().getType() == Material.AIR && KitAPI.hasKit(p, Kits.HULK)
				&& !CooldownAPI.cooldown.containsKey(p.getName()) && p.getPassenger() == null
				&& r.getPassenger() == null) {
			p.setPassenger(r);
			CooldownAPI.addCooldown(p, 15);
			return;
		}
		if (p.getItemInHand().getType() != Material.AIR || !KitAPI.hasKit(p, Kits.HULK)
				|| !CooldownAPI.cooldown.containsKey(p.getName()) || p.getPassenger() != null
				|| r.getPassenger() != null) {
			return;
		}
		if (CooldownAPI.getCooldown(p) > 0L) {
			p.sendMessage(
					"§c§lCOOLDOWN §fAguarde §c" + CooldownAPI.getCooldown(p) + " §fsegundos para utilizar o kit!");
			return;
		}
		CooldownAPI.removeCooldown(p);
		p.setPassenger(r);
		CooldownAPI.addCooldown(p, 15);
	}

	@EventHandler
	public static void playerInteract(final PlayerInteractEvent event) {
		if (!event.getAction().equals(Action.LEFT_CLICK_AIR)) {
			return;
		}
		final Player player = event.getPlayer();
		if (player.getPassenger() == null || !(player.getPassenger() instanceof Player)) {
			return;
		}
		final Player pass = (Player) player.getPassenger();
		player.eject();
		pass.damage(0.0, player);
		pass.setVelocity(new Vector(pass.getVelocity().getX(), 1.0, pass.getVelocity().getZ()));
	}
}
