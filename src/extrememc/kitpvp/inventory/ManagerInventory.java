package extrememc.kitpvp.inventory;

import org.bukkit.inventory.Inventory;
import extrememc.common.itemBuilder.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ManagerInventory
{
    public static String kitSelector;
    public static String warpSelector;
    
    static {
        ManagerInventory.kitSelector = "§eSeus Kits";
        ManagerInventory.warpSelector = "§eWarps";
    }
    
    public static void sendItens(final Player player) {
        final Inventory inventory = (Inventory)player.getInventory();
        inventory.setItem(3, ItemBuilder.criarItem(Material.CHEST, ManagerInventory.kitSelector, "§7Veja todas as suas habilidades."));
        inventory.setItem(5, ItemBuilder.criarItem(Material.PAPER, ManagerInventory.warpSelector, "§7Veja as warps disponivel do servidor."));
        player.updateInventory();
    }
}
