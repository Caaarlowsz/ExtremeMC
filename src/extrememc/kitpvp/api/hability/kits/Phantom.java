package extrememc.kitpvp.api.hability.kits;

import org.bukkit.event.EventHandler;
import extrememc.kitpvp.api.CooldownAPI;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import extrememc.kitpvp.Main;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Phantom implements Listener
{
    private static HashMap<Player, ItemStack[]> armor;
    
    static {
        Phantom.armor = new HashMap<Player, ItemStack[]>();
    }
    
    private static void setPhantom(final Player player) {
        Phantom.armor.put(player, player.getInventory().getArmorContents());
        player.getInventory().setArmorContents((ItemStack[])null);
        player.setAllowFlight(true);
        player.sendMessage("§3§lPHANTOM §fSeu phantom acabar\u00e1 em §c5 segundos...");
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                player.sendMessage("§3§lPHANTOM §fSeu phantom acabar\u00e1 em §c4 segundos...");
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        player.sendMessage("§3§lPHANTOM §fSeu phantom acabar\u00e1 em §c3 segundos...");
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                player.sendMessage("§3§lPHANTOM §fSeu phantom acabar\u00e1 em §c2 segundos...");
                                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        player.sendMessage("§3§lPHANTOM §fSeu phantom acabar\u00e1 em §c1 segundo.");
                                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
                                            @Override
                                            public void run() {
                                                player.sendMessage("§3§lPHANTOM §fSeu phantom acabou!");
                                                player.getInventory().setArmorContents((ItemStack[])null);
                                                player.getInventory().setArmorContents((ItemStack[])Phantom.armor.get(player));
                                                player.setFlying(false);
                                                player.setAllowFlight(false);
                                            }
                                        }, 20L);
                                    }
                                }, 20L);
                            }
                        }, 20L);
                    }
                }, 20L);
            }
        }, 20L);
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals((Object)Material.FEATHER) && KitAPI.hasKit(player, Kits.PHANTOM) && CooldownAPI.cooldown.containsKey(player.getName())) {
            event.setCancelled(true);
            if (CooldownAPI.getCooldown(player) > 0L) {
                player.sendMessage("§c§lCOOLDOWN §fAguarde §c" + CooldownAPI.getCooldown(player) + " §fsegundos para utilizar o kit!");
                return;
            }
            CooldownAPI.removeCooldown(player);
            setPhantom(player);
            CooldownAPI.addCooldown(player, 15);
        }
        else if (player.getItemInHand().getType().equals((Object)Material.FEATHER) && KitAPI.hasKit(player, Kits.PHANTOM) && !CooldownAPI.cooldown.containsKey(player.getCustomName())) {
            setPhantom(player);
            CooldownAPI.addCooldown(player, 15);
        }
    }
}
