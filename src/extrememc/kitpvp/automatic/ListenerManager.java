package extrememc.kitpvp.automatic;

import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import extrememc.kitpvp.Main;

public class ListenerManager
{
    public static void loadListener() {
        for (final Class<?> Classes : ClassGetter.getClassesForPackage(Main.getPlugin((Class)Main.class), "extrememc.kitpvp")) {
            try {
                if (!Listener.class.isAssignableFrom(Classes)) {
                    continue;
                }
                final Listener listener = (Listener)Classes.newInstance();
                Bukkit.getPluginManager().registerEvents(listener, (Plugin)Main.getPlugin((Class)Main.class));
            }
            catch (Exception ex) {}
        }
    }
}
