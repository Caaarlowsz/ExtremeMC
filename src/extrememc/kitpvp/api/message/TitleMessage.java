package extrememc.kitpvp.api.message;

import net.minecraft.server.v1_7_R4.Packet;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import org.spigotmc.ProtocolInjector;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleMessage
{
    public static void sendTitle(final Player player, final String title) {
        if (((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() < 45) {
            return;
        }
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.TITLE, ChatSerializer.a("{\"text\": \"\"}").a(title)));
    }
    
    public static void sendSubTitle(final Player player, final String subtitle) {
        if (((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() < 45) {
            return;
        }
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.SUBTITLE, ChatSerializer.a("{\"text\": \"\"}").a(subtitle)));
    }
    
    public static void clearTitle(final Player player) {
        if (((CraftPlayer)player).getHandle().playerConnection.networkManager.getVersion() < 45) {
            return;
        }
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)new ProtocolInjector.PacketTitle(ProtocolInjector.PacketTitle.Action.CLEAR));
    }
}
