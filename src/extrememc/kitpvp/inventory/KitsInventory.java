package extrememc.kitpvp.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import extrememc.kitpvp.api.hability.KitAPI;
import java.util.Collections;
import extrememc.kitpvp.api.hability.Kits;
import java.util.ArrayList;
import java.util.List;
import extrememc.common.itemBuilder.ItemBuilder;
import java.util.Arrays;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class KitsInventory
{
    public static String title;
    public static String titleAll;
    
    static {
        KitsInventory.title = "§8Seus Kits [1]";
        KitsInventory.titleAll = "§8Todos os Kits [1]";
    }
    
    public static void giveInventory(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, KitsInventory.title);
        inventory.setItem(0, ItemBuilder.criarItem(Material.getMaterial(351), "§cP\u00e1gina anterior.", 8, (List)Arrays.asList("§fN\u00e3o existe p\u00e1gina", "§fAnterior.")));
        inventory.setItem(1, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 3));
        inventory.setItem(2, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 3));
        inventory.setItem(3, ItemBuilder.criarItem(Material.CHEST, "§aSeus Kits", 1, 0));
        inventory.setItem(4, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(5, ItemBuilder.criarItem(Material.ENDER_CHEST, "§7Todos os Kits", 1, 0));
        inventory.setItem(6, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 3));
        inventory.setItem(7, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 3));
        inventory.setItem(8, ItemBuilder.criarItem(Material.getMaterial(351), "§cPr\u00f3xima p\u00e1gina.", 8, (List)Arrays.asList("§fN\u00e3o existe pr\u00f3xima", "§fP\u00e1gina.")));
        inventory.setItem(9, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(10, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(11, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(12, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(13, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(14, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(15, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(16, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        inventory.setItem(17, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 15));
        final ArrayList<String> names = new ArrayList<String>();
        Kits[] arrayOfkitList;
        for (int j = (arrayOfkitList = Kits.values()).length, i = 0; i < j; ++i) {
            final Kits kits = arrayOfkitList[i];
            if (!names.contains(kits.name())) {
                names.add(kits.name());
            }
        }
        Collections.sort(names);
        for (int i = 0; i < names.size(); ++i) {
            Kits[] arrayOfkitList2;
            for (int k = (arrayOfkitList2 = Kits.values()).length, j = 0; j < k; ++j) {
                final Kits list = arrayOfkitList2[j];
                if (names.get(i).equalsIgnoreCase(list.name())) {
                    final ItemStack item = ItemBuilder.criarItem(list.getMaterial(), "§a" + list.getName(), (List)list.getLore());
                    if (!Kits.valueOf(list.name()).equals(Kits.NENHUM) && KitAPI.hasKitPermission(player, Kits.valueOf(list.name()))) {
                        inventory.addItem(new ItemStack[] { item });
                    }
                }
            }
        }
        player.openInventory(inventory);
    }
    
    public static void giveInventoryAll(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, KitsInventory.titleAll);
        inventory.setItem(0, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(1, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(2, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(3, ItemBuilder.criarItem(Material.CHEST, "§7Seus Kits", 1, 0));
        inventory.setItem(4, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(5, ItemBuilder.criarItem(Material.ENDER_CHEST, "§aTodos os Kits", 1, 0));
        inventory.setItem(6, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(7, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(8, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(45, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(46, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(47, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(48, ItemBuilder.criarItem(Material.getMaterial(351), "§7Informa\u00e7\u00f4es", 1, 8));
        inventory.setItem(49, ItemBuilder.criarItem(Material.getMaterial(351), "§aKits", 1, 10));
        inventory.setItem(50, ItemBuilder.criarItem(Material.getMaterial(351), "§7Warps", 1, 8));
        inventory.setItem(51, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(52, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        inventory.setItem(53, ItemBuilder.criarItem(Material.STAINED_GLASS_PANE, "§f ", 1, 1));
        final ArrayList<String> names = new ArrayList<String>();
        Kits[] arrayOfkitList;
        for (int j = (arrayOfkitList = Kits.values()).length, i = 0; i < j; ++i) {
            final Kits kits = arrayOfkitList[i];
            if (!names.contains(kits.name())) {
                names.add(kits.name());
            }
        }
        Collections.sort(names);
        for (int i = 0; i < names.size(); ++i) {
            Kits[] arrayOfkitList2;
            for (int k = (arrayOfkitList2 = Kits.values()).length, j = 0; j < k; ++j) {
                final Kits list = arrayOfkitList2[j];
                if (names.get(i).equalsIgnoreCase(list.name())) {
                    final ItemStack item = ItemBuilder.criarItem(list.getMaterial(), "§a" + list.getName(), (List)list.getLore());
                    if (!Kits.valueOf(list.name()).equals(Kits.NENHUM)) {
                        inventory.addItem(new ItemStack[] { item });
                    }
                }
            }
        }
        player.openInventory(inventory);
    }
}
