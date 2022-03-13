package extrememc.kitpvp.automatic;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;

public class ListenerManager {
	public static void loadListener() {
		for (final Class<?> Classes : ClassGetter.getClassesForPackage(ExtremePvP.getPlugin(ExtremePvP.class),
				"extrememc.kitpvp")) {
			try {
				if (!Listener.class.isAssignableFrom(Classes)) {
					continue;
				}
				final Listener listener = (Listener) Classes.newInstance();
				Bukkit.getPluginManager().registerEvents(listener, ExtremePvP.getPlugin(ExtremePvP.class));
			} catch (Exception ex) {
			}
		}
	}
}
