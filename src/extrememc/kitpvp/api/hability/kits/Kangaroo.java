package extrememc.kitpvp.api.hability.kits;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;

public class Kangaroo implements Listener {
	public HashMap<Player, Integer> Pulo;
	public ArrayList<Player> cooldownKangaroo;

	public Kangaroo() {
		this.Pulo = new HashMap<Player, Integer>();
		this.cooldownKangaroo = new ArrayList<Player>();
	}

	@EventHandler
	public void interact(final PlayerInteractEvent event) {
		final Player p = event.getPlayer();
		if (KitAPI.hasKit(p, Kits.KANGAROO) && p.getItemInHand().getType() == Material.FIREWORK) {
			event.setCancelled(true);
			if (!this.cooldownKangaroo.contains(p)) {
				if (!this.Pulo.containsKey(p)) {
					if (!p.isSneaking()) {
						if (!((CraftPlayer) p).getHandle().onGround) {
							this.Pulo.put(p, 1);
							p.setVelocity(new Vector(p.getVelocity().getX(), 1.0, p.getVelocity().getZ()));
						} else {
							p.setVelocity(new Vector(p.getVelocity().getX(), 1.0, p.getVelocity().getZ()));
						}
					} else if (!((CraftPlayer) p).getHandle().onGround) {
						p.setVelocity(p.getLocation().getDirection().multiply(1.5));
						p.setVelocity(new Vector(p.getVelocity().getX(), 0.5, p.getVelocity().getZ()));
						this.Pulo.put(p, 1);
					} else {
						p.setVelocity(p.getLocation().getDirection().multiply(1.5));
						p.setVelocity(new Vector(p.getVelocity().getX(), 0.5, p.getVelocity().getZ()));
					}
				}
			} else {
				p.sendMessage("�c�lCOMBATE �fVoc\u00ea tomou um hit recentemente!");
			}
		}
	}

	@EventHandler
	public void landed(final PlayerMoveEvent e) {
		if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() != Material.AIR
				&& this.Pulo.containsKey(e.getPlayer())) {
			this.Pulo.remove(e.getPlayer());
		}
	}

	@EventHandler
	public void gotdamage(final EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (KitAPI.hasKit(p, Kits.KANGAROO) && e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) {
				if (!(e.getDamager() instanceof Player)) {
					return;
				}
				if (!this.cooldownKangaroo.contains(p)) {
					this.cooldownKangaroo.add(p);
					new BukkitRunnable() {
						public void run() {
							Kangaroo.this.cooldownKangaroo.remove(p);
						}
					}.runTaskLater(ExtremePvP.getPlugin(), 100L);
				}
			}
		}
	}

	@EventHandler
	public void entitydamage(final EntityDamageEvent e) {
		if (e.getCause() == EntityDamageEvent.DamageCause.FALL && e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (KitAPI.hasKit(p, Kits.KANGAROO)) {
				if (e.getDamage() > 7.0) {
					e.setDamage(7.0);
				} else {
					e.setDamage(e.getDamage());
				}
			}
		}
	}

	public boolean isOnGround(final Player p) {
		Location l = p.getLocation();
		l = l.add(0.0, -1.0, 0.0);
		return l.getBlock().getState().getTypeId() != 0;
	}
}
