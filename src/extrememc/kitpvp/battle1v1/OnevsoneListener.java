package extrememc.kitpvp.battle1v1;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;
import extrememc.kitpvp.protectionManager.ProtectionManager;

public class OnevsoneListener implements Listener {

	public int itemsInInventory(final Inventory inventory, final Material[] search) {
		final List<Material> wanted = Arrays.asList(search);
		int found = 0;
		ItemStack[] arrayOfItemStack;
		for (int j = (arrayOfItemStack = inventory.getContents()).length, i = 0; i < j; ++i) {
			final ItemStack item = arrayOfItemStack[i];
			if (item != null && wanted.contains(item.getType())) {
				found += item.getAmount();
			}
		}
		return found;
	}

	@EventHandler
	public void playerInteractEvent(final PlayerInteractEntityEvent event) {
		final Player player = event.getPlayer();
		final Player target = (Player) event.getRightClicked();
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		if (!OnevsoneManager.inWarp.contains(player.getUniqueId())) {
			return;
		}
		if (!player.getItemInHand().equals(OnevsoneManager.blaze_rod)) {
			return;
		}
		if (OnevsoneManager.cooldown.contains(player.getUniqueId())) {
			player.sendMessage("�c�l1V1 �fAguarde um pouco para desafiar novamente!");
			return;
		}
		OnevsoneManager.invite.add(target.getUniqueId());
		OnevsoneManager.invited.add(player.getUniqueId());
		if (OnevsoneManager.invite.contains(player.getUniqueId())
				&& OnevsoneManager.invited.contains(target.getUniqueId())) {
			OnevsoneManager.startMatch(player, target);
			OnevsoneManager.invite.remove(target.getUniqueId());
			OnevsoneManager.invited.remove(player.getUniqueId());
			ProtectionManager.removeProtection(player);
			ProtectionManager.removeProtection(target);
			target.sendMessage("�a�l1V1 �f" + player.getName() + " aceitou seu desafio!");
			player.sendMessage("�a�l1V1 �fVoc\u00ea aceitou o desafio do player " + target.getName());
			return;
		}
		player.sendMessage("�a�l1V1 �fVoc\u00ea desafiou o player " + target.getName());
		target.sendMessage("�a�l1V1 �fVoc\u00ea foi desafiado por " + player.getName());
		OnevsoneManager.cooldown.add(player.getUniqueId());
		new BukkitRunnable() {
			public void run() {
				if (OnevsoneManager.cooldown.contains(player.getUniqueId())) {
					OnevsoneManager.cooldown.remove(player.getUniqueId());
					OnevsoneManager.invite.remove(target.getUniqueId());
					OnevsoneManager.invited.remove(player.getUniqueId());
					this.cancel();
				}
			}
		}.runTaskLater(ExtremePvP.getInstance(), 250L);
	}

	@EventHandler
	public void playerInteract(final PlayerInteractEvent event) {
		final Player player = event.getPlayer();
		if (!OnevsoneManager.inWarp.contains(player.getUniqueId())) {
			return;
		}
		if (player.getItemInHand().equals(OnevsoneManager.gray_color)) {
			if (OnevsoneManager.fast.contains(player)) {
				return;
			}
			event.setCancelled(true);
			OnevsoneManager.fast.add(player);
			player.getInventory().remove(OnevsoneManager.gray_color);
			player.getInventory().setItem(5, OnevsoneManager.green_color);
			player.updateInventory();
			player.playSound(player.getLocation(), Sound.CLICK, 100.0f, 100.0f);
			player.sendMessage("�a�l1V1 �fVoc\u00ea entrou na fila do 1v1 r\u00e1pido!");
			if (OnevsoneManager.fast.size() == 2) {
				final Player player_1 = OnevsoneManager.fast.get(0);
				final Player player_2 = OnevsoneManager.fast.get(1);
				if (player_1 == null) {
					OnevsoneManager.fast.remove(player_1);
				}
				if (player_2 == null) {
					OnevsoneManager.fast.remove(player_2);
				}
				if (player_1 != null && player_2 != null) {
					OnevsoneManager.startMatch(player_1, player_2);
					ProtectionManager.removeProtection(player_1);
					ProtectionManager.removeProtection(player_2);
					OnevsoneManager.fast.remove(player_1);
					OnevsoneManager.fast.remove(player_2);
					player_1.sendMessage("�a�l1V1 �fVoc\u00ea ir\u00e1 batalhar contra " + player_2.getName());
					player_2.sendMessage("�a�l1V1 �fVoc\u00ea ir\u00e1 batalhar contra " + player_1.getName());
				}
			} else {
				new BukkitRunnable() {
					public void run() {
						if (OnevsoneManager.fast.contains(player)
								&& player.getInventory().getItem(5).equals(OnevsoneManager.green_color)) {
							OnevsoneManager.fast.remove(player);
							player.getInventory().remove(OnevsoneManager.green_color);
							player.getInventory().setItem(5, OnevsoneManager.gray_color);
							player.updateInventory();
							player.sendMessage("�c�l1V1 �fNenhuma batalha foi localizada!");
							this.cancel();
						}
					}
				}.runTaskLater(ExtremePvP.getPlugin(), 200L);
			}
		} else {
			if (!player.getItemInHand().equals(OnevsoneManager.green_color)) {
				return;
			}
			if (!OnevsoneManager.fast.contains(player)) {
				return;
			}
			OnevsoneManager.fast.remove(player);
			player.getInventory().remove(OnevsoneManager.green_color);
			player.getInventory().setItem(5, OnevsoneManager.gray_color);
			player.updateInventory();
			player.playSound(player.getLocation(), Sound.WOOD_CLICK, 100.0f, 100.0f);
			player.sendMessage("�c�l1V1 �fVoc\u00ea saiu da fila do 1v1 r\u00e1pido!");
		}
	}

	@EventHandler
	public void processocommand(final PlayerCommandPreprocessEvent e) {
		final Player p = e.getPlayer();
		if (OnevsoneManager.inWarp.contains(e.getPlayer().getUniqueId())
				&& OnevsoneManager.fighting.containsKey(p.getUniqueId())) {
			p.sendMessage("�c�l1V1 �fComandos na warp 1v1 n\u00e3o s\u00e3o permitidos em batalha.");
			e.setCancelled(true);
		}
	}
}
