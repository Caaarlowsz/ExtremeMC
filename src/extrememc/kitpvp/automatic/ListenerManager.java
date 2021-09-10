package extrememc.kitpvp.automatic;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import extrememc.kitpvp.Main;

public class ListenerManager {
	public static void loadListener() {
		for (final Class<?> Classes : ClassGetter.getClassesForPackage(Main.getPlugin(Main.class),
				"extrememc.kitpvp")) {
			try {
				if (!Listener.class.isAssignableFrom(Classes)) {
					continue;
				}
				final Listener listener = (Listener) Classes.newInstance();
				Bukkit.getPluginManager().registerEvents(listener, Main.getPlugin(Main.class));
			} catch (Exception ex) {
			}
		}
	}
}
