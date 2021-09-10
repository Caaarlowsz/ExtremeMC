package extrememc.kitpvp.api.message;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.spigotmc.ProtocolInjector;

import net.minecraft.server.v1_7_R4.ChatSerializer;

public class TitleMessage {
	public static void sendTitle(final Player player, final String title) {
		if (((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion() < 45) {
			return;
		}
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(
				ProtocolInjector.PacketTitle.Action.TITLE, ChatSerializer.a("{\"text\": \"\"}").a(title)));
	}

	public static void sendSubTitle(final Player player, final String subtitle) {
		if (((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion() < 45) {
			return;
		}
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new ProtocolInjector.PacketTitle(
				ProtocolInjector.PacketTitle.Action.SUBTITLE, ChatSerializer.a("{\"text\": \"\"}").a(subtitle)));
	}

	public static void clearTitle(final Player player) {
		if (((CraftPlayer) player).getHandle().playerConnection.networkManager.getVersion() < 45) {
			return;
		}
		((CraftPlayer) player).getHandle().playerConnection
				.sendPacket(new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.CLEAR));
	}
}
