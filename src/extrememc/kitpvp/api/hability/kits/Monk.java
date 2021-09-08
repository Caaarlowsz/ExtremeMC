package extrememc.kitpvp.api.hability.kits;

import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import java.util.Random;
import extrememc.kitpvp.api.CooldownAPI;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.Listener;

public class Monk implements Listener
{
    @EventHandler
    public void monkHabilidade(final PlayerInteractEntityEvent e) {
        final Player p = e.getPlayer();
        if (!(e.getRightClicked() instanceof Player)) {
            return;
        }
        final Player jogadorClicado = (Player)e.getRightClicked();
        if (p.getItemInHand().getType() != Material.BLAZE_ROD || !KitAPI.hasKit(p, Kits.MONK) || !CooldownAPI.cooldown.containsKey(p.getName())) {
            if (p.getItemInHand().getType() == Material.BLAZE_ROD && KitAPI.hasKit(p, Kits.MONK) && !CooldownAPI.cooldown.containsKey(p.getName())) {
                final int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);
                final ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
                final ItemStack ItemMudado = jogadorClicado.getItemInHand();
                jogadorClicado.setItemInHand(ItemSelecionado);
                jogadorClicado.getInventory().setItem(random, ItemMudado);
                CooldownAPI.addCooldown(p, 35);
            }
            return;
        }
        e.setCancelled(true);
        if (CooldownAPI.getCooldown(p) > 0L) {
            p.sendMessage("§c§lCOOLDOWN §fAguarde §c" + CooldownAPI.getCooldown(p) + " §fsegundos para utilizar o kit!");
            return;
        }
        CooldownAPI.removeCooldown(p);
        final int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);
        final ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
        final ItemStack ItemMudado = jogadorClicado.getItemInHand();
        jogadorClicado.setItemInHand(ItemSelecionado);
        jogadorClicado.getInventory().setItem(random, ItemMudado);
        CooldownAPI.addCooldown(p, 35);
    }
}
