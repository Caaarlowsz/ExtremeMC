package extrememc.kitpvp.api.message;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_7_R4.ChatSerializer;
import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.PacketPlayOutChat;

public class ActionMessage {
	private static int VERSION;

	static {
		ActionMessage.VERSION = 47;
	}

	public static void sendActionBar(final Player p, final String text) {
		final CraftPlayer craftplayer = (CraftPlayer) p;
		final IChatBaseComponent ichatbasecomponent = ChatSerializer.a("{\"text\": \"" + text + "\"}");
		final PacketPlayOutChat packetplayoutchat = new PacketPlayOutChat(ichatbasecomponent, 2);
		if (craftplayer.getHandle().playerConnection.networkManager.getVersion() != ActionMessage.VERSION) {
			return;
		}
		craftplayer.getHandle().playerConnection.sendPacket(packetplayoutchat);
	}
}
