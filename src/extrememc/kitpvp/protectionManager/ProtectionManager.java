package extrememc.kitpvp.protectionManager;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.event.Listener;

public class ProtectionManager implements Listener
{
    public static ArrayList<Player> invencible;
    
    static {
        ProtectionManager.invencible = new ArrayList<Player>();
    }
    
    public static boolean hasProtection(final Player player) {
        return ProtectionManager.invencible.contains(player);
    }
    
    public static void setProtection(final Player player) {
        if (!hasProtection(player)) {
            ProtectionManager.invencible.add(player);
        }
    }
    
    public static void removeProtection(final Player player) {
        if (hasProtection(player)) {
            ProtectionManager.invencible.remove(player);
        }
    }
    
    @EventHandler
    public void asd(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() != EntityDamageEvent.DamageCause.LAVA) {
            final Player player = (Player)event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.LAVA) {
                player.damage(2.0);
            }
            if (hasProtection(player)) {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void asd(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            final Player player = (Player)event.getDamager();
            if (hasProtection(player)) {
                event.setCancelled(true);
            }
        }
    }
}
