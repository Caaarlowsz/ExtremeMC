package extrememc.kitpvp;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.Material;
import org.bukkit.Location;
import extrememc.kitpvp.api.hability.kits.Gladiator;
import org.bukkit.event.HandlerList;
import extrememc.kitpvp.commands.SetwarpCommand;
import extrememc.kitpvp.commands.SpawnCommand;
import extrememc.kitpvp.commands.BuildCommand;
import extrememc.kitpvp.commands.KitsCommand;
import org.bukkit.command.CommandExecutor;
import extrememc.kitpvp.commands.AdminCommand;
import java.util.Iterator;
import extrememc.kitpvp.scoreboard.Scoreboarding;
import extrememc.kitpvp.automatic.ListenerManager;
import org.bukkit.entity.Item;
import org.bukkit.entity.Entity;
import org.bukkit.World;
import extrememc.kitpvp.automatic.TeleportServer;
import org.bukkit.Bukkit;
import java.util.UUID;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public static ArrayList<Player> checkBelowName;
    public static Main instance;
    public static Plugin plugin;
    public static boolean fullkit;
    public static ArrayList<UUID> scoreboard;
    
    static {
        Main.checkBelowName = new ArrayList<Player>();
        Main.scoreboard = new ArrayList<UUID>();
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public static Plugin getPlugin() {
        return Main.plugin;
    }
    
    public void onEnable() {
        Main.instance = this;
        Main.plugin = (Plugin)this;
        Bukkit.getMessenger().registerOutgoingPluginChannel((Plugin)this, "BungeeCord");
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player todos = onlinePlayers[i];
            TeleportServer.sendTeleport(todos, TeleportServer.Servers.LOBBY);
        }
        for (final World dia : Bukkit.getServer().getWorlds()) {
            dia.setGameRuleValue("doDaylightCycle", "false");
        }
        Player[] onlinePlayers2;
        for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
            final Player player = onlinePlayers2[j];
            for (final Entity entity : player.getWorld().getEntities()) {
                if (entity instanceof Item) {
                    entity.remove();
                }
            }
        }
        this.saveDefaultConfig();
        ListenerManager.loadListener();
        this.loadCommand();
        Scoreboarding.updateScore();
        Main.fullkit = false;
    }
    
    public void loadCommand() {
        this.registerCommand((CommandExecutor)new AdminCommand());
        this.registerCommand((CommandExecutor)new KitsCommand());
        this.registerCommand((CommandExecutor)new BuildCommand());
        this.registerCommand((CommandExecutor)new SpawnCommand());
        this.registerCommand((CommandExecutor)new SetwarpCommand());
    }
    
    public void registerCommand(final CommandExecutor command) {
        final String name = command.getClass().getSimpleName().split("Command")[0].toLowerCase();
        this.getCommand(name).setExecutor(command);
    }
    
    public void onDisable() {
        HandlerList.unregisterAll();
        Main.instance = null;
        Main.plugin = null;
        Player[] onlinePlayers;
        for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
            final Player todos = onlinePlayers[i];
            if (Gladiator.fighting.containsKey(todos.getName())) {
                final Location loc = Gladiator.localizacao.get(todos);
                final List<Location> cuboid = new ArrayList<Location>();
                cuboid.clear();
                for (int bX = -10; bX <= 10; ++bX) {
                    for (int bZ = -10; bZ <= 10; ++bZ) {
                        for (int bY = -1; bY <= 10; ++bY) {
                            if (bY == 10) {
                                cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                            }
                            else if (bY == -1) {
                                cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                            }
                            else if (bX == -10 || bZ == -10 || bX == 10 || bZ == 10) {
                                cuboid.add(loc.clone().add((double)bX, (double)bY, (double)bZ));
                            }
                        }
                    }
                }
                for (final Location loc2 : cuboid) {
                    loc2.getBlock().setType(Material.AIR);
                    if (Gladiator.bloco.containsKey(loc2)) {
                        Gladiator.bloco.get(loc2).setType(Material.AIR);
                    }
                }
            }
        }
    }
    
    public List<Player> getOnlinePlayers() {
        final List<Player> list = new ArrayList<Player>();
        for (final World world : getInstance().getServer().getWorlds()) {
            for (final Player player : world.getPlayers()) {
                list.add(player);
            }
        }
        return list;
    }
}
