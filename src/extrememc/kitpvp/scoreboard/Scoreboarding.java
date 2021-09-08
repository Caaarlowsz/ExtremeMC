package extrememc.kitpvp.scoreboard;

import net.minecraft.server.v1_7_R4.IChatBaseComponent;
import net.minecraft.server.v1_7_R4.Packet;
import org.spigotmc.ProtocolInjector;
import net.minecraft.server.v1_7_R4.ChatSerializer;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.OfflinePlayer;
import extrememc.common.statusManager.Liga;
import extrememc.kitpvp.api.warp.WarpsAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import extrememc.kitpvp.Main;
import org.bukkit.Bukkit;
import extrememc.kitpvp.battle1v1.OnevsoneManager;
import extrememc.kitpvp.api.hability.KitAPI;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Objective;
import java.text.NumberFormat;
import extrememc.common.playerManager.PlayerManager;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.entity.Player;

public class Scoreboarding
{
    public static int time;
    
    static {
        Scoreboarding.time = 0;
    }
    
    public static void setScoreboard(final Player player) {
        sendScoreboard(player);
        sendTab(player, "\n§7PvP §b§lWAVE\n", "\n§fTwitter §b@_WaveMC\n §fDiscord §bbit.ly/WaveDiscord_ \n§fLoja §bloja.wave-mc.com\n");
    }
    
    public static void updateKills(final Player player, final Scoreboard scoreboard) {
        final PlayerManager playerManager = new PlayerManager(player);
        if (player == null) {
            return;
        }
        if (scoreboard == null) {
            return;
        }
        if (!player.isOnline()) {
            return;
        }
        final Objective obj = scoreboard.getObjective("score");
        obj.setDisplayName("§b§lPVP");
        Team team = null;
        team = null;
        if (scoreboard.getTeam("kills") == null) {
            team = scoreboard.registerNewTeam("kills");
        }
        else {
            team = scoreboard.getTeam("kills");
        }
        team.setSuffix(" §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getKills()));
    }
    
    public static void updateDeaths(final Player player, final Scoreboard scoreboard) {
        final PlayerManager playerManager = new PlayerManager(player);
        if (player == null) {
            return;
        }
        if (scoreboard == null) {
            return;
        }
        if (!player.isOnline()) {
            return;
        }
        final Objective obj = scoreboard.getObjective("score");
        obj.setDisplayName("§b§lPVP");
        Team team = null;
        team = null;
        if (scoreboard.getTeam("deaths") == null) {
            team = scoreboard.registerNewTeam("deaths");
        }
        else {
            team = scoreboard.getTeam("deaths");
        }
        team.setSuffix(" §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getDeaths()));
    }
    
    public static void updateStreak(final Player player, final Scoreboard scoreboard) {
        final PlayerManager playerManager = new PlayerManager(player);
        if (player == null) {
            return;
        }
        if (scoreboard == null) {
            return;
        }
        if (!player.isOnline()) {
            return;
        }
        final Objective obj = scoreboard.getObjective("score");
        obj.setDisplayName("§b§lPVP");
        Team team = null;
        team = null;
        if (scoreboard.getTeam("streak") == null) {
            team = scoreboard.registerNewTeam("streak");
        }
        else {
            team = scoreboard.getTeam("streak");
        }
        team.setSuffix(" §b" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getStreak()));
    }
    
    public static void updateXp(final Player player, final Scoreboard scoreboard) {
        final PlayerManager playerManager = new PlayerManager(player);
        if (player == null) {
            return;
        }
        if (scoreboard == null) {
            return;
        }
        if (!player.isOnline()) {
            return;
        }
        final Objective obj = scoreboard.getObjective("score");
        obj.setDisplayName("§b§lPVP");
        Team team = null;
        team = null;
        if (scoreboard.getTeam("xp") == null) {
            team = scoreboard.registerNewTeam("xp");
        }
        else {
            team = scoreboard.getTeam("xp");
        }
        team.setSuffix(" §b" + NumberFormat.getInstance().format(playerManager.getStatusGlobal().getXp()));
    }
    
    public static void updateModes(final Player player, final Scoreboard scoreboard) {
        final PlayerManager playerManager = new PlayerManager(player);
        if (player == null) {
            return;
        }
        if (scoreboard == null) {
            return;
        }
        if (!player.isOnline()) {
            return;
        }
        final Objective obj = scoreboard.getObjective("score");
        obj.setDisplayName("§6§lPVP");
        Team team = null;
        team = null;
        if (scoreboard.getTeam("moedas") == null) {
            team = scoreboard.registerNewTeam("moedas");
        }
        else {
            team = scoreboard.getTeam("moedas");
        }
        team.setSuffix(" §e" + NumberFormat.getInstance().format(playerManager.getStatusGlobal().getMoedas()));
    }
    
    public static void updateKit(final Player player, final Scoreboard scoreboard) {
        if (player == null) {
            return;
        }
        if (scoreboard == null) {
            return;
        }
        if (!player.isOnline()) {
            return;
        }
        final Objective obj = scoreboard.getObjective("score");
        Team team = null;
        team = null;
        if (scoreboard.getTeam("kit") == null) {
            team = scoreboard.registerNewTeam("kit");
        }
        else {
            team = scoreboard.getTeam("kit");
        }
        team.setSuffix(" §b" + KitAPI.getKitName(player));
    }
    
    public static String getMod(final String name) {
        if (name.length() == 16) {
            final String shorts = name.substring(0, name.length() - 4);
            return shorts;
        }
        if (name.length() == 15) {
            final String shorts = name.substring(0, name.length() - 3);
            return shorts;
        }
        if (name.length() == 14) {
            final String shorts = name.substring(0, name.length() - 2);
            return shorts;
        }
        if (name.length() == 13) {
            final String shorts = name.substring(0, name.length() - 1);
            return shorts;
        }
        return name;
    }
    
    public static void updateOpponent(final Player player, final Scoreboard scoreboard) {
        if (player == null) {
            return;
        }
        if (scoreboard == null) {
            return;
        }
        if (!player.isOnline()) {
            return;
        }
        final Objective obj = scoreboard.getObjective("score");
        Team team = null;
        team = null;
        if (scoreboard.getTeam("opponent") == null) {
            team = scoreboard.registerNewTeam("opponent");
        }
        else {
            team = scoreboard.getTeam("opponent");
        }
        final String opponente = OnevsoneManager.opponent.get(player.getUniqueId());
        if (OnevsoneManager.opponent.get(player.getUniqueId()) == null) {
            team.setSuffix("§bNingu\u00e9m");
        }
        else {
            team.setSuffix("§b" + getMod(opponente));
        }
    }
    
    public static void updateScore() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
                if (Scoreboarding.time == 1) {
                    Player[] onlinePlayers;
                    for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, i = 0; i < length; ++i) {
                        final Player all = onlinePlayers[i];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§3§lWAVE PVP");
                    }
                }
                if (Scoreboarding.time == 2) {
                    Player[] onlinePlayers2;
                    for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, j = 0; j < length2; ++j) {
                        final Player all = onlinePlayers2[j];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§3§lWAVE PV§b§lP");
                    }
                }
                if (Scoreboarding.time == 3) {
                    Player[] onlinePlayers3;
                    for (int length3 = (onlinePlayers3 = Bukkit.getOnlinePlayers()).length, k = 0; k < length3; ++k) {
                        final Player all = onlinePlayers3[k];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§3§lWAVE P§b§lV§f§lP");
                    }
                }
                if (Scoreboarding.time == 4) {
                    Player[] onlinePlayers4;
                    for (int length4 = (onlinePlayers4 = Bukkit.getOnlinePlayers()).length, l = 0; l < length4; ++l) {
                        final Player all = onlinePlayers4[l];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§3§lWAVE §b§lP§f§lVP");
                    }
                }
                if (Scoreboarding.time == 5) {
                    Player[] onlinePlayers5;
                    for (int length5 = (onlinePlayers5 = Bukkit.getOnlinePlayers()).length, n = 0; n < length5; ++n) {
                        final Player all = onlinePlayers5[n];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§3§lWAV§b§lE §f§lPVP");
                    }
                }
                if (Scoreboarding.time == 6) {
                    Player[] onlinePlayers6;
                    for (int length6 = (onlinePlayers6 = Bukkit.getOnlinePlayers()).length, n2 = 0; n2 < length6; ++n2) {
                        final Player all = onlinePlayers6[n2];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§3§lWA§b§lV§f§lE PVP");
                    }
                }
                if (Scoreboarding.time == 7) {
                    Player[] onlinePlayers7;
                    for (int length7 = (onlinePlayers7 = Bukkit.getOnlinePlayers()).length, n3 = 0; n3 < length7; ++n3) {
                        final Player all = onlinePlayers7[n3];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§3§lW§b§lA§f§lVE PVP");
                    }
                }
                if (Scoreboarding.time == 8) {
                    Player[] onlinePlayers8;
                    for (int length8 = (onlinePlayers8 = Bukkit.getOnlinePlayers()).length, n4 = 0; n4 < length8; ++n4) {
                        final Player all = onlinePlayers8[n4];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§b§lW§f§lAVE PVP");
                    }
                }
                if (Scoreboarding.time == 9) {
                    Player[] onlinePlayers9;
                    for (int length9 = (onlinePlayers9 = Bukkit.getOnlinePlayers()).length, n5 = 0; n5 < length9; ++n5) {
                        final Player all = onlinePlayers9[n5];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§f§lWAVE PVP");
                    }
                }
                if (Scoreboarding.time == 10) {
                    Player[] onlinePlayers10;
                    for (int length10 = (onlinePlayers10 = Bukkit.getOnlinePlayers()).length, n6 = 0; n6 < length10; ++n6) {
                        final Player all = onlinePlayers10[n6];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§f§lWAVE PVP");
                    }
                }
                if (Scoreboarding.time == 11) {
                    Player[] onlinePlayers11;
                    for (int length11 = (onlinePlayers11 = Bukkit.getOnlinePlayers()).length, n7 = 0; n7 < length11; ++n7) {
                        final Player all = onlinePlayers11[n7];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§b§lWAVE PVP");
                    }
                }
                if (Scoreboarding.time == 12) {
                    Player[] onlinePlayers12;
                    for (int length12 = (onlinePlayers12 = Bukkit.getOnlinePlayers()).length, n8 = 0; n8 < length12; ++n8) {
                        final Player all = onlinePlayers12[n8];
                        all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§b§lWAVE PVP");
                    }
                    Scoreboarding.time = 0;
                }
                ++Scoreboarding.time;
            }
        }, 0L, 2L);
    }
    
    public static void sendScoreboard(final Player player) {
        if (!Main.scoreboard.contains(player.getUniqueId())) {
            return;
        }
        final Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective obj = sb.registerNewObjective("score", "dummy");
        obj.setDisplayName("§b§lWAVE PVP");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        final PlayerManager playerManager = new PlayerManager(player);
        final Liga liga = playerManager.getStatusGlobal().getLiga();
        if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.FPS)) {
            makeScore("§3", null, null, null, sb, 10);
            makeScore("§fKills:", "kills", " §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getKills()), null, sb, 9);
            makeScore("§fDeaths:", "deaths", " §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getDeaths()), null, sb, 8);
            makeScore("§fKillStreak:", "streak", " §b" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getStreak()), null, sb, 7);
            makeScore("§2", null, null, null, sb, 6);
            makeScore("§fRank:", "liga", " " + liga.getColor() + liga.getSymbol() + " " + liga.name(), null, sb, 5);
            makeScore("§fJogadores:", "jogadores", " §7" + Bukkit.getOnlinePlayers().length, null, sb, 4);
            makeScore("§0", null, null, null, sb, 3);
            makeScore("§fWarp:", "warp", " §b" + WarpsAPI.getPlayerWarp(player), null, sb, 2);
            makeScore("§6", null, null, null, sb, 1);
            makeScore("§bwww.wavemc", "site", ".com.br", null, sb, 0);
            player.setScoreboard(sb);
            return;
        }
        if (WarpsAPI.isInWarp(player, WarpsAPI.Warps.ONEVSONE)) {
            makeScore("§3", null, null, null, sb, 9);
            makeScore("§fKills:", "kills", " §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getKills()), null, sb, 8);
            makeScore("§fDeaths:", "deaths", " §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getDeaths()), null, sb, 7);
            makeScore("§fKillStreak:", "streak", " §b" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getStreak()), null, sb, 6);
            makeScore("§2", null, null, null, sb, 5);
            makeScore("§fBatalhando", "batalhando", " §fcontra:", null, sb, 4);
            final String opponente = OnevsoneManager.opponent.get(player.getUniqueId());
            if (OnevsoneManager.opponent.get(player.getUniqueId()) == null) {
                makeScore("§1", "opponent", "§bNingu\u00e9m", null, sb, 3);
            }
            else {
                makeScore("§1", "opponent", "§b" + getMod(opponente), null, sb, 3);
            }
            makeScore("§0", null, null, null, sb, 2);
            makeScore("§bwww.wavemc", "site", ".com.br", null, sb, 1);
            player.setScoreboard(sb);
            return;
        }
        makeScore("§3", null, null, null, sb, 10);
        makeScore("§fKills:", "kills", " §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getKills()), null, sb, 9);
        makeScore("§fDeaths:", "deaths", " §3" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getDeaths()), null, sb, 8);
        makeScore("§fKillStreak:", "streak", " §b" + NumberFormat.getInstance().format(playerManager.getStatusKitPvP().getStreak()), null, sb, 7);
        makeScore("§2", null, null, null, sb, 6);
        makeScore("§fRank:", "liga", " " + liga.getColor() + liga.getSymbol() + " " + liga.name(), null, sb, 5);
        makeScore("§fJogadores:", "jogadores", " §7" + Bukkit.getOnlinePlayers().length, null, sb, 4);
        makeScore("§0", null, null, null, sb, 3);
        makeScore("§fKit:", "kit", " §b" + KitAPI.getKitName(player), null, sb, 2);
        makeScore("§6", null, null, null, sb, 1);
        makeScore("§bwww.wavemc", "site", ".com.br", null, sb, 0);
        player.setScoreboard(sb);
    }
    
    public static void removeScoreboard(final Player player) {
        if (Main.scoreboard.contains(player.getUniqueId())) {
            return;
        }
        player.getScoreboard().getObjective("score").unregister();
        player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
    }
    
    private static FastOfflinePlayer getFastOfflinePlayer(final String string, final String teamname, final String suffix, final String prefix, final Scoreboard sb) {
        final FastOfflinePlayer fast = new FastOfflinePlayer(string);
        if (teamname != null) {
            Team team = null;
            if (sb.getTeam(teamname) == null) {
                team = sb.registerNewTeam(teamname);
            }
            else {
                team = sb.getTeam(teamname);
            }
            if (suffix != null) {
                team.setSuffix(suffix);
            }
            if (prefix != null) {
                team.setPrefix(prefix);
            }
            team.addPlayer((OfflinePlayer)fast);
        }
        return fast;
    }
    
    private static void makeScore(final String string, final String teamname, final String suffix, final String prefix, final Scoreboard sb, final int integer) {
        Objective obj = null;
        if (sb.getObjective("score") == null) {
            obj = sb.registerNewObjective("score", "dummy");
        }
        else {
            obj = sb.getObjective("score");
        }
        obj.getScore((OfflinePlayer)getFastOfflinePlayer(string, teamname, suffix, prefix, sb)).setScore(integer);
    }
    
    public static void sendTab(final Player p, final String cima, final String baixo) {
        if (((CraftPlayer)p).getHandle().playerConnection.networkManager.getVersion() < 46) {
            return;
        }
        final IChatBaseComponent tabcima = ChatSerializer.a("{\"text\": \"" + cima + "\"}");
        final IChatBaseComponent tabbaixo = ChatSerializer.a("{\"text\": \"" + baixo + "\"}");
        final ProtocolInjector.PacketTabHeader packet = new ProtocolInjector.PacketTabHeader(tabcima, tabbaixo);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}
