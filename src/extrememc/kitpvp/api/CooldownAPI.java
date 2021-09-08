package extrememc.kitpvp.api;

import org.bukkit.plugin.Plugin;
import extrememc.kitpvp.Main;
import org.bukkit.Bukkit;
import java.util.concurrent.TimeUnit;
import org.bukkit.entity.Player;
import java.util.HashMap;

public class CooldownAPI
{
    public static HashMap<String, Long> cooldown;
    
    static {
        CooldownAPI.cooldown = new HashMap<String, Long>();
    }
    
    public static void addCooldown(final Player player, final Integer time) {
        CooldownAPI.cooldown.put(player.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(time));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                CooldownAPI.removeCooldown(player);
            }
        }, (long)(time * 20));
    }
    
    public static void removeCooldown(final Player player) {
        CooldownAPI.cooldown.remove(player.getName());
    }
    
    public static boolean hashCooldown(final Player player) {
        return CooldownAPI.cooldown.containsKey(player.getName());
    }
    
    public static Long getCooldown(final Player player) {
        final Long time = TimeUnit.MILLISECONDS.toSeconds(CooldownAPI.cooldown.get(player.getName()) - System.currentTimeMillis());
        if (CooldownAPI.cooldown.containsKey(player.getName()) || CooldownAPI.cooldown.get(player.getName()) > System.currentTimeMillis()) {
            return time;
        }
        return 0L;
    }
}
