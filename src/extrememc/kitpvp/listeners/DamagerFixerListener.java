package extrememc.kitpvp.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import extrememc.kitpvp.Main;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import extrememc.kitpvp.api.hability.KitAPI;
import extrememc.kitpvp.api.hability.Kits;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.Listener;

public class DamagerFixerListener implements Listener
{
    @EventHandler
    protected void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        final Player player = (Player)e.getEntity();
        final Player damager = (Player)e.getDamager();
        if (KitAPI.hasKit(player, Kits.NENHUM) || KitAPI.hasKit(damager, Kits.NENHUM)) {
            return;
        }
        final Scoreboard scoreboard = damager.getScoreboard();
        if (scoreboard.getObjective(DisplaySlot.BELOW_NAME) != null) {
            return;
        }
        final Objective below = scoreboard.registerNewObjective("lucas", "god");
        final String kit = "§b" + KitAPI.getKitName(player);
        below.setDisplayName(kit);
        below.setDisplaySlot(DisplaySlot.BELOW_NAME);
        new BukkitRunnable() {
            public void run() {
                scoreboard.clearSlot(DisplaySlot.BELOW_NAME);
                below.unregister();
            }
        }.runTaskLater(Main.getPlugin(), 20L);
    }
    
    @EventHandler
    public void asd(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            final Player p = (Player)e.getDamager();
            final ItemStack sword = p.getItemInHand();
            double damage = e.getDamage() - 1.0;
            final double danoEspada = this.getDamage(sword.getType());
            boolean isMore = false;
            if (damage > 1.0) {
                isMore = true;
            }
            if (!sword.getEnchantments().isEmpty()) {
                if (sword.containsEnchantment(Enchantment.DAMAGE_ARTHROPODS) && this.isArthropod(e.getEntityType())) {
                    damage -= 1.15 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
                    damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS);
                }
                if (sword.containsEnchantment(Enchantment.DAMAGE_UNDEAD) && this.isUndead(e.getEntityType())) {
                    damage -= 1.15 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
                    damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD);
                }
                if (sword.containsEnchantment(Enchantment.DAMAGE_ALL)) {
                    damage -= 1.15 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
                    damage += 1 * sword.getEnchantmentLevel(Enchantment.DAMAGE_ALL);
                }
            }
            if (this.isCrital(p)) {
                damage -= danoEspada / 1.5;
                ++damage;
            }
            if (isMore) {
                --damage;
            }
            if (damage > 8.5) {
                damage = 7.5;
            }
            final Material type = sword.getType();
            if (type != Material.STONE_SWORD && type != Material.WOOD_SWORD && type != Material.DIAMOND_SWORD) {
                damage = 0.5;
            }
            e.setDamage(damage);
        }
    }
    
    private boolean isCrital(final Player p) {
        return p.getFallDistance() > 0.0f && !p.isOnGround() && !p.hasPotionEffect(PotionEffectType.BLINDNESS);
    }
    
    private boolean isArthropod(final EntityType type) {
        switch (type) {
            case CAVE_SPIDER: {
                return true;
            }
            case SPIDER: {
                return true;
            }
            case SILVERFISH: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private boolean isUndead(final EntityType type) {
        switch (type) {
            case SKELETON: {
                return true;
            }
            case ZOMBIE: {
                return true;
            }
            case WITHER_SKULL: {
                return true;
            }
            case PIG_ZOMBIE: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    private double getDamage(final Material type) {
        double damage = 1.0;
        if (type.toString().contains("DIAMOND_")) {
            damage = 5.0;
        }
        else if (type.toString().contains("IRON_")) {
            damage = 4.0;
        }
        else if (type.toString().contains("STONE_")) {
            damage = 3.0;
        }
        else if (type.toString().contains("WOOD_")) {
            damage = 2.5;
        }
        else if (type.toString().contains("GOLD_")) {
            damage = 3.0;
        }
        if (!type.toString().contains("_SWORD")) {
            --damage;
            if (!type.toString().contains("_AXE")) {
                --damage;
                if (!type.toString().contains("_PICKAXE")) {
                    --damage;
                    if (!type.toString().contains("_SPADE")) {
                        damage = 1.0;
                    }
                }
            }
        }
        return damage;
    }
}
