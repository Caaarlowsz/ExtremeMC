package extrememc.kitpvp.automatic;

import com.google.common.io.ByteArrayDataOutput;
import org.bukkit.plugin.Plugin;
import extrememc.kitpvp.Main;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

public class TeleportServer
{
    public static void sendTeleport(final Player player, final Servers server) {
        final ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server.getServer().toLowerCase());
        player.sendPluginMessage((Plugin)Main.getInstance(), "BungeeCord", out.toByteArray());
    }
    
    public enum Servers
    {
        LOGIN("LOGIN", 0, "login"), 
        LOBBY("LOBBY", 1, "lobby"), 
        PVP("PVP", 2, "pvp"), 
        SCREENSHARE("SCREENSHARE", 3, "screenshare"), 
        HG_A1("HG_A1", 4, "hg-a1"), 
        HG_A2("HG_A2", 5, "hg-a2"), 
        HG_A3("HG_A3", 6, "hg-a3"), 
        HG_A4("HG_A4", 7, "hg-a4"), 
        HG_A5("HG_A5", 8, "hg-a5");
        
        private String server;
        
        private Servers(final String s, final int n, final String server) {
            this.server = server;
        }
        
        public String getServer() {
            return this.server;
        }
    }
}
