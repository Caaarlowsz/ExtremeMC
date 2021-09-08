package extrememc.kitpvp.scoreboard;

import java.util.Map;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import com.google.common.base.Charsets;
import java.util.UUID;
import org.bukkit.OfflinePlayer;

public class FastOfflinePlayer implements OfflinePlayer
{
    private final String playerName;
    
    public FastOfflinePlayer(final String playerName) {
        this.playerName = playerName;
    }
    
    public boolean isOnline() {
        return false;
    }
    
    public String getName() {
        return this.playerName;
    }
    
    public UUID getUniqueId() {
        return UUID.nameUUIDFromBytes(this.playerName.getBytes(Charsets.UTF_8));
    }
    
    public boolean isBanned() {
        return false;
    }
    
    public void setBanned(final boolean banned) {
        throw new UnsupportedOperationException();
    }
    
    public boolean isWhitelisted() {
        return false;
    }
    
    public void setWhitelisted(final boolean value) {
        throw new UnsupportedOperationException();
    }
    
    public Player getPlayer() {
        throw new UnsupportedOperationException();
    }
    
    public long getFirstPlayed() {
        return System.currentTimeMillis();
    }
    
    public long getLastPlayed() {
        return System.currentTimeMillis();
    }
    
    public boolean hasPlayedBefore() {
        return false;
    }
    
    public Location getBedSpawnLocation() {
        throw new UnsupportedOperationException();
    }
    
    public boolean isOp() {
        return false;
    }
    
    public void setOp(final boolean value) {
        throw new UnsupportedOperationException();
    }
    
    public Map<String, Object> serialize() {
        throw new UnsupportedOperationException();
    }
}
