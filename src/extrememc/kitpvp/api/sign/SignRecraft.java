package extrememc.kitpvp.api.sign;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.Sign;
import extrememc.kitpvp.api.warp.WarpsAPI;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.Listener;

public class SignRecraft implements Listener
{
    public String Line1;
    public String Line2;
    public String Line3;
    public String Line4;
    
    public SignRecraft() {
        this.Line1 = "§bWave§fMC";
        this.Line2 = "";
        this.Line3 = "§eRecraft";
        this.Line4 = "§7(Clique)";
    }
    
    @EventHandler
    public void signRecraft(final SignChangeEvent event) {
        if (event.getLine(0).equals("recraft")) {
            event.setLine(0, this.Line1);
            event.setLine(1, this.Line2);
            event.setLine(2, this.Line3);
            event.setLine(3, this.Line4);
        }
    }
    
    @EventHandler
    public void signClickRecraft(final PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN)) {
            if (WarpsAPI.isInWarp(e.getPlayer(), WarpsAPI.Warps.SPAWN)) {
                return;
            }
            final Sign sign = (Sign)e.getClickedBlock().getState();
            if (sign.getLine(0).equalsIgnoreCase(this.Line1) && sign.getLine(1).equalsIgnoreCase(this.Line2) && sign.getLine(2).equalsIgnoreCase(this.Line3) && sign.getLine(3).equalsIgnoreCase(this.Line4)) {
                final ItemStack coguvermelho = new ItemStack(Material.RED_MUSHROOM, 64);
                final ItemMeta n = coguvermelho.getItemMeta();
                n.setDisplayName("§aCogumelo Vermelho");
                coguvermelho.setItemMeta(n);
                final ItemStack cogumarrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
                final ItemMeta m = cogumarrom.getItemMeta();
                m.setDisplayName("§aCogumelo Marrom");
                cogumarrom.setItemMeta(m);
                final ItemStack pote = new ItemStack(Material.BOWL, 64);
                final ItemMeta o = pote.getItemMeta();
                o.setDisplayName("§aPote");
                pote.setItemMeta(o);
                final Inventory v = Bukkit.createInventory((InventoryHolder)null, 27, "§8Menu - Recraft");
                v.setItem(0, coguvermelho);
                v.setItem(1, pote);
                v.setItem(2, cogumarrom);
                v.setItem(3, coguvermelho);
                v.setItem(4, pote);
                v.setItem(5, cogumarrom);
                v.setItem(6, coguvermelho);
                v.setItem(7, pote);
                v.setItem(8, cogumarrom);
                v.setItem(9, coguvermelho);
                v.setItem(10, pote);
                v.setItem(11, cogumarrom);
                v.setItem(12, coguvermelho);
                v.setItem(13, pote);
                v.setItem(14, cogumarrom);
                v.setItem(15, coguvermelho);
                v.setItem(16, pote);
                v.setItem(17, cogumarrom);
                v.setItem(18, coguvermelho);
                v.setItem(19, pote);
                v.setItem(20, cogumarrom);
                v.setItem(21, coguvermelho);
                v.setItem(22, pote);
                v.setItem(23, cogumarrom);
                v.setItem(24, coguvermelho);
                v.setItem(25, pote);
                v.setItem(26, cogumarrom);
                e.getPlayer().openInventory(v);
            }
        }
    }
}
