package extrememc.kitpvp.inventory;

import org.bukkit.inventory.Inventory;
import extrememc.common.itemBuilder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class WarpInventory
{
    public static String title;
    
    static {
        WarpInventory.title = "§8Menu - Warps";
    }
    
    public static void giveInventory(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 9, WarpInventory.title);
        inventory.setItem(0, ItemBuilder.criarItem(Material.GLASS, "§aFps"));
        inventory.setItem(1, ItemBuilder.criarItem(Material.BLAZE_ROD, "§a1v1"));
        inventory.setItem(2, ItemBuilder.criarItem(Material.IRON_FENCE, "§aGladiator"));
        player.openInventory(inventory);
    }
}
