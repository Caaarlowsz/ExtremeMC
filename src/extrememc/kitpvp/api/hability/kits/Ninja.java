package extrememc.kitpvp.api.hability.kits;

import java.text.DecimalFormat;
import extrememc.kitpvp.api.warp.WarpsAPI;
import extrememc.kitpvp.api.CooldownAPI;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.EventHandler;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import extrememc.kitpvp.Main;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.event.Listener;

public class Ninja implements Listener
{
    public static HashMap<Player, Player> a;
    public static HashMap<Player, Long> b;
    public static Main plugin;
    
    static {
        Ninja.a = new HashMap<Player, Player>();
        Ninja.b = new HashMap<Player, Long>();
    }
    
    @EventHandler
    public void a(final EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
        if (paramEntityDamageByEntityEvent.getDamager() instanceof Player && paramEntityDamageByEntityEvent.getEntity() instanceof Player) {
            final Player localPlayer1 = (Player)paramEntityDamageByEntityEvent.getDamager();
            final Player localPlayer2 = (Player)paramEntityDamageByEntityEvent.getEntity();
            if (KitAPI.hasKit(localPlayer1, Kits.NINJA)) {
                Ninja.a.put(localPlayer1, localPlayer2);
            }
        }
    }
    
    @EventHandler
    public void a(final PlayerToggleSneakEvent paramPlayerToggleSneakEvent) {
        final Player localPlayer1 = paramPlayerToggleSneakEvent.getPlayer();
        if (!paramPlayerToggleSneakEvent.isSneaking() || !KitAPI.hasKit(localPlayer1, Kits.NINJA) || !CooldownAPI.cooldown.containsKey(localPlayer1.getName())) {
            if (paramPlayerToggleSneakEvent.isSneaking() && KitAPI.hasKit(localPlayer1, Kits.NINJA) && Ninja.a.containsKey(localPlayer1)) {
                final Player localPlayer2 = Ninja.a.get(localPlayer1);
                if (localPlayer2 != null && !localPlayer2.isDead()) {
                    if (WarpsAPI.isInWarp(localPlayer2, WarpsAPI.Warps.SPAWN)) {
                        return;
                    }
                    if (Gladiator.noExecut.contains(localPlayer2)) {
                        localPlayer1.sendMessage("§3§lNINJA §fO \u00faltimo jogador hitado est\u00e1 em um Gladiator no momento.");
                        return;
                    }
                    if (Gladiator.noExecut.contains(localPlayer1)) {
                        localPlayer1.sendMessage("§3§lNINJA §fVoc\u00ea n\u00e3o pode utilizar o kit Ninja em Gladiator.");
                        return;
                    }
                    String str = null;
                    if (Ninja.b.get(localPlayer1) != null) {
                        final long l = Ninja.b.get(localPlayer1) - System.currentTimeMillis();
                        final DecimalFormat localDecimalFormat = new DecimalFormat("##");
                        final int i = (int)l / 1000;
                        str = localDecimalFormat.format(i);
                    }
                }
                if (Ninja.b.get(localPlayer1) == null || Ninja.b.get(localPlayer1) < System.currentTimeMillis()) {
                    if (localPlayer1.getLocation().distance(localPlayer2.getLocation()) < 85.0) {
                        localPlayer1.teleport(localPlayer2.getLocation());
                        CooldownAPI.addCooldown(localPlayer1, 7);
                        Ninja.b.put(localPlayer1, System.currentTimeMillis() + 10000L);
                    }
                    else {
                        localPlayer1.sendMessage("§3§lNINJA §fO \u00faltimo jogador hitado est\u00e1 muito longe.");
                    }
                }
            }
            return;
        }
        if (CooldownAPI.getCooldown(localPlayer1) > 0L) {
            localPlayer1.sendMessage("§c§lCOOLDOWN §fAguarde §c" + CooldownAPI.getCooldown(localPlayer1) + " §fsegundos para utilizar o kit!");
            return;
        }
        CooldownAPI.removeCooldown(localPlayer1);
        if (paramPlayerToggleSneakEvent.isSneaking() && KitAPI.hasKit(localPlayer1, Kits.NINJA) && Ninja.a.containsKey(localPlayer1)) {
            final Player localPlayer2 = Ninja.a.get(localPlayer1);
            if (localPlayer2 != null && !localPlayer2.isDead()) {
                if (Gladiator.noExecut.contains(localPlayer2)) {
                    localPlayer1.sendMessage("§3§lNINJA §fO \u00faltimo jogador hitado est\u00e1 em um Gladiato no momento.");
                    return;
                }
                if (Gladiator.noExecut.contains(localPlayer1)) {
                    localPlayer1.sendMessage("§3§lNINJA §fVoc\u00ea n\u00e3o pode utilizar o kit Ninja em Gladiator.");
                    return;
                }
                String str = null;
                if (Ninja.b.get(localPlayer1) != null) {
                    final long l = Ninja.b.get(localPlayer1) - System.currentTimeMillis();
                    final DecimalFormat localDecimalFormat = new DecimalFormat("##");
                    final int i = (int)l / 1000;
                    str = localDecimalFormat.format(i);
                }
            }
            if (Ninja.b.get(localPlayer1) == null || Ninja.b.get(localPlayer1) < System.currentTimeMillis()) {
                if (localPlayer1.getLocation().distance(localPlayer2.getLocation()) < 85.0) {
                    localPlayer1.teleport(localPlayer2.getLocation());
                    CooldownAPI.addCooldown(localPlayer1, 6);
                    Ninja.b.put(localPlayer1, System.currentTimeMillis() + 10000L);
                }
                else {
                    localPlayer1.sendMessage("§3§lNINJA §fO \u00faltimo jogador hitado est\u00e1 muito longe.");
                }
            }
        }
    }
}
