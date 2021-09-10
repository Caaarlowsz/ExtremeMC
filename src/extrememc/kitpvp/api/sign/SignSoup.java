package extrememc.kitpvp.api.sign;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import extrememc.kitpvp.api.warp.WarpsAPI;

public class SignSoup implements Listener {
	public String Line1;
	public String Line2;
	public String Line3;
	public String Line4;

	public SignSoup() {
		this.Line1 = "§bWave§fMC";
		this.Line2 = "";
		this.Line3 = "§eSopas";
		this.Line4 = "§7(Clique)";
	}

	@EventHandler
	public void signSoup(final SignChangeEvent event) {
		if (event.getLine(0).equals("sopas")) {
			event.setLine(0, this.Line1);
			event.setLine(1, this.Line2);
			event.setLine(2, this.Line3);
			event.setLine(3, this.Line4);
		}
	}

	@EventHandler
	public void signClickSoup(final PlayerInteractEvent e) {
		final ItemStack sopa = new ItemStack(Material.MUSHROOM_SOUP);
		final ItemMeta sopas = sopa.getItemMeta();
		sopa.setItemMeta(sopas);
		if (WarpsAPI.isInWarp(e.getPlayer(), WarpsAPI.Warps.SPAWN)) {
			return;
		}
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN
				|| e.getClickedBlock().getType() == Material.SIGN_POST
				|| e.getClickedBlock().getType() == Material.WALL_SIGN)) {
			final Sign sign = (Sign) e.getClickedBlock().getState();
			if (sign.getLine(0).equalsIgnoreCase(this.Line1) && sign.getLine(1).equalsIgnoreCase(this.Line2)
					&& sign.getLine(2).equalsIgnoreCase(this.Line3) && sign.getLine(3).equalsIgnoreCase(this.Line4)) {
				final Inventory v = Bukkit.createInventory((InventoryHolder) null, 36, "§8Menu - Sopas");
				for (int i = 0; i < 36; ++i) {
					v.addItem(new ItemStack(sopa));
				}
				e.getPlayer().openInventory(v);
			}
		}
	}
}
