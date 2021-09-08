package extrememc.kitpvp.api.hability.kits;

import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.GameMode;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import java.util.Iterator;
import java.util.List;
import extrememc.kitpvp.Main;
import org.bukkit.Bukkit;
import extrememc.kitpvp.commands.BuildCommand;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import org.bukkit.Material;
import extrememc.kitpvp.api.warp.WarpsAPI;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.block.Block;
import org.bukkit.Location;
import java.util.HashMap;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.event.Listener;

public class Gladiator implements Listener
{
    public boolean generateGlass;
    public static ArrayList<Player> noExecut;
    public static HashMap<String, Location> oldl;
    public static HashMap<String, String> fighting;
    public HashMap<Integer, ArrayList<Location>> blocks;
    public static HashMap<Player, Location> localizacao;
    public static HashMap<Location, Block> bloco;
    public HashMap<Integer, String[]> players;
    public HashMap<String, Integer> tasks;
    int nextID;
    public static int id1;
    public static int id2;
    
    static {
        Gladiator.noExecut = new ArrayList<Player>();
        Gladiator.oldl = new HashMap<String, Location>();
        Gladiator.fighting = new HashMap<String, String>();
        Gladiator.localizacao = new HashMap<Player, Location>();
        Gladiator.bloco = new HashMap<Location, Block>();
    }
    
    public Gladiator() {
        this.generateGlass = true;
        this.blocks = new HashMap<Integer, ArrayList<Location>>();
        this.players = new HashMap<Integer, String[]>();
        this.tasks = new HashMap<String, Integer>();
        this.nextID = 0;
    }
    
    @EventHandler
    public void OnGladiatorKit(final PlayerInteractEntityEvent event) {
        final Player p = event.getPlayer();
        if (event.getRightClicked() instanceof Player) {
            final Player r = (Player)event.getRightClicked();
            if (WarpsAPI.isInWarp(r, WarpsAPI.Warps.SPAWN)) {
                return;
            }
            if (p.getItemInHand().getType() == Material.IRON_FENCE && KitAPI.hasKit(p, Kits.GLADIATOR)) {
                event.setCancelled(true);
                final Location loc = new Location(p.getWorld(), (double)p.getLocation().getBlockX(), (double)(p.getLocation().getBlockY() + 70), (double)p.getLocation().getBlockZ());
                Gladiator.localizacao.put(p, loc);
                Gladiator.localizacao.put(r, loc);
                final Location loc2 = new Location(p.getWorld(), (double)(p.getLocation().getBlockX() + 8), (double)(p.getLocation().getBlockY() + 73), (double)(p.getLocation().getBlockZ() + 8));
                final Location loc3 = new Location(p.getWorld(), (double)(p.getLocation().getBlockX() - 8), (double)(p.getLocation().getBlockY() + 73), (double)(p.getLocation().getBlockZ() - 8));
                if (Gladiator.fighting.containsKey(p.getName()) || Gladiator.fighting.containsKey(r.getName())) {
                    event.setCancelled(true);
                    return;
                }
                final Integer currentID = this.nextID;
                ++this.nextID;
                final ArrayList<String> list = new ArrayList<String>();
                list.add(p.getName());
                list.add(r.getName());
                this.players.put(currentID, list.toArray(new String[1]));
                Gladiator.oldl.put(p.getName(), p.getLocation());
                Gladiator.oldl.put(r.getName(), r.getLocation());
                if (this.generateGlass) {
                    final List<Location> cuboid = new ArrayList<Location>();
                    cuboid.clear();
                    for (int bX = -10; bX <= 10; ++bX) {
                        for (int bZ = -10; bZ <= 10; ++bZ) {
                            for (int bY = -1; bY <= 10; ++bY) {
                                final Block b = loc.clone().add((double)bX, (double)bY, (double)bZ).getBlock();
                                if (!b.isEmpty()) {
                                    event.setCancelled(true);
                                    p.sendMessage("§c§lERRO §fVoc\u00ea n\u00e3o pode puxar nesse local.");
                                    return;
                                }
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
                    for (final Location loc4 : cuboid) {
                        loc4.getBlock().setType(Material.GLASS);
                        Gladiator.bloco.put(loc4, loc4.getBlock());
                    }
                    loc2.setYaw(135.0f);
                    p.teleport(loc2);
                    loc3.setYaw(-45.0f);
                    r.teleport(loc3);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
                    r.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 110, 5));
                    BuildCommand.Build.put(p.getName(), BuildCommand.BuildModes.ON);
                    BuildCommand.Build.put(r.getName(), BuildCommand.BuildModes.ON);
                    Gladiator.noExecut.add(p);
                    Gladiator.noExecut.add(r);
                    Gladiator.fighting.put(p.getName(), r.getName());
                    Gladiator.fighting.put(r.getName(), p.getName());
                    Gladiator.id1 = Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (Gladiator.fighting.containsKey(p.getName()) && Gladiator.fighting.get(p.getName()).equalsIgnoreCase(r.getName()) && Gladiator.fighting.containsKey(r.getName()) && Gladiator.fighting.get(r.getName()).equalsIgnoreCase(p.getName())) {
                                Gladiator.fighting.remove(p.getName());
                                Gladiator.fighting.remove(r.getName());
                                Gladiator.noExecut.remove(p);
                                Gladiator.noExecut.remove(r);
                                p.teleport((Location)Gladiator.oldl.get(p.getName()));
                                r.teleport((Location)Gladiator.oldl.get(r.getName()));
                                Gladiator.oldl.remove(p.getName());
                                Gladiator.oldl.remove(r.getName());
                                final Location loc = Gladiator.localizacao.get(p);
                                final List<Location> cuboid = new ArrayList<Location>();
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
                                    Gladiator.bloco.get(loc2).setType(Material.AIR);
                                }
                            }
                        }
                    }, 100000L);
                }
            }
        }
    }
    
    @EventHandler
    public void onSpongePlace(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() != Material.COBBLESTONE) {
            return;
        }
        final int spongesleft = p.getItemInHand().getAmount();
        e.setCancelled(true);
        final Location placed = e.getBlock().getLocation();
        final World w = placed.getWorld();
        final double x = placed.getX();
        final double y = placed.getY();
        final double z = placed.getZ();
        final Location sponge = new Location(w, x, y, z);
        final Material block = e.getBlockReplacedState().getType();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                sponge.getBlock().setType(Material.COBBLESTONE);
            }
        }, 1L);
        if (p.getItemInHand().getAmount() == 1) {
            p.setItemInHand(new ItemStack(Material.AIR));
        }
        p.getItemInHand().setAmount(spongesleft - 1);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, (Runnable)new Runnable() {
            @Override
            public void run() {
                sponge.getBlock().setType(block);
            }
        }, 250L);
    }
    
    @EventHandler
    public void onPlayerInteractGlad(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.IRON_FENCE && KitAPI.hasKit(p, Kits.GLADIATOR)) {
            e.setCancelled(true);
            p.updateInventory();
        }
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlyaerInteract(final PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.GLASS && e.getPlayer().getGameMode() != GameMode.CREATIVE && Gladiator.fighting.containsKey(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getClickedBlock().setType(Material.GLASS);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), (Runnable)new Runnable() {
                @Override
                public void run() {
                    if (Gladiator.fighting.containsKey(e.getPlayer().getName())) {
                        e.getClickedBlock().setType(Material.GLASS);
                    }
                }
            }, 30L);
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLeft(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (Gladiator.fighting.containsKey(p.getName())) {
            final Player t = Bukkit.getServer().getPlayer((String)Gladiator.fighting.get(p.getName()));
            Gladiator.fighting.remove(t.getName());
            Gladiator.fighting.remove(p.getName());
            Gladiator.noExecut.remove(p);
            Gladiator.noExecut.remove(t);
            Gladiator.fighting.remove(t.getName());
            Gladiator.fighting.remove(p.getName());
            Gladiator.noExecut.remove(p);
            Gladiator.noExecut.remove(t);
            final Location old = Gladiator.oldl.get(t.getName());
            t.teleport(old);
            Bukkit.getScheduler().cancelTask(Gladiator.id1);
            Bukkit.getScheduler().cancelTask(Gladiator.id2);
            t.removePotionEffect(PotionEffectType.WITHER);
            final Location loc = Gladiator.localizacao.get(p);
            final List<Location> cuboid = new ArrayList<Location>();
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
                Gladiator.bloco.get(loc2).setType(Material.AIR);
            }
            for (final Location loc2 : cuboid) {
                loc2.getBlock().setType(Material.AIR);
                Gladiator.bloco.get(loc2).setType(Material.AIR);
            }
            for (final Location loc2 : cuboid) {
                loc2.getBlock().setType(Material.AIR);
                Gladiator.bloco.get(loc2).setType(Material.AIR);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeathGladiator(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        if (Gladiator.fighting.containsKey(p.getName())) {
            final Player k = Bukkit.getServer().getPlayer((String)Gladiator.fighting.get(p.getName()));
            final Location old = Gladiator.oldl.get(p.getName());
            k.teleport(old);
            Bukkit.getScheduler().cancelTask(Gladiator.id1);
            Bukkit.getScheduler().cancelTask(Gladiator.id2);
            k.removePotionEffect(PotionEffectType.WITHER);
            k.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 10));
            Gladiator.fighting.remove(k.getName());
            Gladiator.fighting.remove(p.getName());
            Gladiator.noExecut.remove(p);
            Gladiator.noExecut.remove(k);
            BuildCommand.Build.put(k.getName(), BuildCommand.BuildModes.OFF);
            BuildCommand.Build.put(p.getName(), BuildCommand.BuildModes.OFF);
            final Location loc = Gladiator.localizacao.get(p);
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
    
    @EventHandler
    public void processocommand(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (Gladiator.noExecut.contains(p) && !e.getMessage().toLowerCase().startsWith("/report") && !e.getMessage().toLowerCase().startsWith("/sc") && !e.getMessage().toLowerCase().startsWith("/score") && !e.getMessage().toLowerCase().startsWith("/tag") && !e.getMessage().toLowerCase().startsWith("/ban") && extrememc.common.Main.getInstance().getPermissions().isMod(p)) {
            e.setCancelled(true);
            p.sendMessage("§c§lCOMANDO §fEsse comando esta bloqueado durante o gladiator.");
        }
    }
}
