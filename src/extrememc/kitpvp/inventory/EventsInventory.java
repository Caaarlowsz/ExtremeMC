package extrememc.kitpvp.inventory;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Inventory;
import extrememc.kitpvp.api.warp.WarpManager;
import extrememc.kitpvp.api.warp.WarpsAPI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.Listener;

public class EventsInventory implements Listener
{
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals((Object)Material.CHEST) && (player.getItemInHand().getItemMeta().hasDisplayName() & player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ManagerInventory.kitSelector))) {
            event.setCancelled(true);
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                KitsInventory.giveInventory(player);
            }
            return;
        }
        if (player.getItemInHand().getType().equals((Object)Material.PAPER) && (player.getItemInHand().getItemMeta().hasDisplayName() & player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ManagerInventory.warpSelector))) {
            event.setCancelled(true);
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                WarpInventory.giveInventory(player);
            }
        }
    }
    
    @EventHandler
    public void onClickead(final InventoryClickEvent event) {
        final Inventory inventory = event.getInventory();
        final ItemStack currentItem = event.getCurrentItem();
        final Entity whoClicked = (Entity)event.getWhoClicked();
        if (event.getWhoClicked() instanceof Player) {
            final Player player = (Player)whoClicked;
            if (currentItem == null) {
                return;
            }
            if (!currentItem.hasItemMeta()) {
                return;
            }
            if (inventory.getTitle().equals(KitsInventory.title)) {
                event.setCancelled(true);
                if (currentItem.getItemMeta().getDisplayName().equals("§7Warps")) {
                    WarpInventory.giveInventory(player);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§aKits")) {
                    event.setCancelled(true);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§f ") && currentItem.getItemMeta().getDisplayName().equals("§aKits") && currentItem.getItemMeta().getDisplayName().equals("§cP\u00e1gina anterior.") && currentItem.getItemMeta().getDisplayName().equals("§cPr\u00f3xima p\u00e1gina!")) {
                    event.setCancelled(true);
                    return;
                }
                if (currentItem.getType() != Material.STAINED_GLASS_PANE && currentItem.getType() != Material.CHEST && currentItem.getType() != Material.ENDER_CHEST && currentItem.getType() != Material.GOLD_INGOT && currentItem.getType() != Material.getMaterial(351)) {
                    player.closeInventory();
                    player.chat("/kit " + currentItem.getItemMeta().getDisplayName().replace("§a", "").replace("§f", ""));
                    return;
                }
            }
            if (inventory.getTitle().equals(KitsInventory.titleAll)) {
                event.setCancelled(true);
                if (currentItem.getItemMeta().getDisplayName().equals("§7Warps")) {
                    WarpInventory.giveInventory(player);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§aKits")) {
                    event.setCancelled(true);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§f ")) {
                    event.setCancelled(true);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§7Seus Kits")) {
                    KitsInventory.giveInventory(player);
                    return;
                }
                if (currentItem.getType() != Material.STAINED_GLASS_PANE && currentItem.getType() != Material.CHEST && currentItem.getType() != Material.ENDER_CHEST && currentItem.getType() != Material.GOLD_INGOT && currentItem.getType() != Material.getMaterial(351)) {
                    player.closeInventory();
                    player.chat("/kit " + currentItem.getItemMeta().getDisplayName().replace("§a", "").replace("§f", ""));
                    return;
                }
            }
            if (inventory.getTitle().equals(WarpInventory.title)) {
                event.setCancelled(true);
                if (currentItem.getItemMeta().getDisplayName().equals("§7Kits")) {
                    KitsInventory.giveInventory(player);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§aWarps")) {
                    event.setCancelled(true);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§f ")) {
                    event.setCancelled(true);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§aFps")) {
                    WarpManager.send(player, WarpsAPI.Warps.FPS);
                    return;
                }
                if (currentItem.getItemMeta().getDisplayName().equals("§a1v1")) {
                    WarpManager.send(player, WarpsAPI.Warps.ONEVSONE);
                }
            }
        }
    }
}
