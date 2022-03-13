package extrememc.kitpvp.api.hability;

import java.util.HashMap;

import com.github.caaarlowsz.extrememc.kitpvp.ExtremePvP;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import extrememc.common.Main;
import extrememc.common.itemBuilder.ItemBuilder;
import extrememc.common.strings.Strings;
import extrememc.kitpvp.api.warp.WarpsAPI;
import extrememc.kitpvp.protectionManager.ProtectionManager;
import extrememc.kitpvp.scoreboard.Scoreboarding;

public class KitAPI {
	public static HashMap<Player, Kits> Kit;

	static {
		KitAPI.Kit = new HashMap<Player, Kits>();
	}

	public static void setKit(final Player player, final Kits kit) {
		KitAPI.Kit.put(player, kit);
		WarpsAPI.setWarp(player, WarpsAPI.Warps.ARENA);
		ProtectionManager.removeProtection(player);
		Scoreboarding.setScoreboard(player);
	}

	public static Kits getKit(final Player player) {
		return KitAPI.Kit.get(player);
	}

	public static boolean isUsingKits(final Player player) {
		return !KitAPI.Kit.get(player).equals(Kits.NENHUM);
	}

	public static boolean hasKit(final Player player, final Kits kit) {
		final Kits playerkit = KitAPI.Kit.get(player);
		return kit.equals(playerkit);
	}

	public static void removeKit(final Player player) {
		KitAPI.Kit.put(player, Kits.NENHUM);
	}

	public static String getKitName(final Player player) {
		if (hasKit(player, Kits.NENHUM)) {
			return "Nenhum";
		}
		if (hasKit(player, Kits.ANTISTOMPER)) {
			return "AntiStomper";
		}
		if (hasKit(player, Kits.PVP)) {
			return "PvP";
		}
		if (hasKit(player, Kits.ARCHER)) {
			return "Archer";
		}
		if (hasKit(player, Kits.ANCHOR)) {
			return "Anchor";
		}
		if (hasKit(player, Kits.KANGAROO)) {
			return "Kangaroo";
		}
		if (hasKit(player, Kits.FISHERMAN)) {
			return "Fisherman";
		}
		if (hasKit(player, Kits.MONK)) {
			return "Monk";
		}
		if (hasKit(player, Kits.NINJA)) {
			return "Ninja";
		}
		if (hasKit(player, Kits.PHANTOM)) {
			return "Phantom";
		}
		if (hasKit(player, Kits.VIPER)) {
			return "Viper";
		}
		if (hasKit(player, Kits.SNAIL)) {
			return "Snail";
		}
		if (hasKit(player, Kits.HULK)) {
			return "Hulk";
		}
		if (hasKit(player, Kits.THOR)) {
			return "Thor";
		}
		if (hasKit(player, Kits.SWITCHER)) {
			return "Switcher";
		}
		if (hasKit(player, Kits.GLADIATOR)) {
			return "Gladiator";
		}
		if (hasKit(player, Kits.STOMPER)) {
			return "Stomper";
		}
		if (hasKit(player, Kits.MAGMA)) {
			return "Magma";
		}
		if (hasKit(player, Kits.GRANDPA)) {
			return "Grandpa";
		}
		if (hasKit(player, Kits.TURTLE)) {
			return "Turtle";
		}
		if (hasKit(player, Kits.SPECIALIST)) {
			return "Specialist";
		}
		return "Nenhum";
	}

	public static void giveItens(final Player player) {
		final PlayerInventory inv = player.getInventory();
		inv.clear();
		player.setFireTicks(0);
		player.setHealth(20.0);
		player.setFlying(false);
		inv.setItem(13, ItemBuilder.criarItem(Material.RED_MUSHROOM, "�aCogumelo Vermelho", 64));
		inv.setItem(14, ItemBuilder.criarItem(Material.BROWN_MUSHROOM, "�aCogumelo Marrom", 64));
		inv.setItem(15, ItemBuilder.criarItem(Material.BOWL, "�aPote", 64));
		for (int i = 0; i < 34; ++i) {
			inv.addItem(ItemBuilder.criarItem(Material.MUSHROOM_SOUP));
		}
		final ItemStack swordpvp = new ItemStack(Material.STONE_SWORD);
		final ItemMeta swordPvPMeta = swordpvp.getItemMeta();
		swordPvPMeta.spigot().setUnbreakable(true);
		swordPvPMeta.setDisplayName("�aEspada");
		swordPvPMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		swordpvp.setItemMeta(swordPvPMeta);
		final ItemStack sword = new ItemStack(Material.STONE_SWORD);
		final ItemMeta swordMeta = sword.getItemMeta();
		swordMeta.spigot().setUnbreakable(true);
		swordMeta.setDisplayName("�aEspada");
		sword.setItemMeta(swordMeta);
		if (hasKit(player, Kits.PVP)) {
			inv.setItem(0, swordpvp);
		} else {
			inv.setItem(0, sword);
		}
		inv.setItem(8, ItemBuilder.criarItem(Material.COMPASS, "�aB\u00fassola"));
		player.setGameMode(GameMode.SURVIVAL);
		player.updateInventory();
	}

	public static void giveKit(final Player player, final Kits kit) {
		final PlayerInventory inv = player.getInventory();
		final ItemStack bow = new ItemStack(Material.BOW);
		final ItemMeta bowm = bow.getItemMeta();
		bowm.setDisplayName(String.valueOf(Strings.primaryColor) + "Arco");
		bowm.spigot().setUnbreakable(true);
		bowm.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		bowm.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		bow.setItemMeta(bowm);
		if (kit.equals(Kits.ARCHER)) {
			inv.setItem(9, ItemBuilder.criarItem(Material.ARROW, String.valueOf(Strings.primaryColor) + "Flecha", 1));
			inv.setItem(1, bow);
			player.updateInventory();
		}
		if (kit.equals(Kits.KANGAROO)) {
			inv.setItem(1, ItemBuilder.criarItem(Material.FIREWORK, String.valueOf(Strings.primaryColor) + "Kangaroo"));
			player.updateInventory();
		}
		if (kit.equals(Kits.FISHERMAN)) {
			inv.setItem(1,
					ItemBuilder.criarItem(Material.FISHING_ROD, String.valueOf(Strings.primaryColor) + "Fisherman"));
			player.updateInventory();
		}
		if (kit.equals(Kits.MONK)) {
			inv.setItem(1, ItemBuilder.criarItem(Material.BLAZE_ROD, String.valueOf(Strings.primaryColor) + "Monk"));
			player.updateInventory();
		}
		if (kit.equals(Kits.PHANTOM)) {
			inv.setItem(1, ItemBuilder.criarItem(Material.FEATHER, String.valueOf(Strings.primaryColor) + "Phantom"));
			player.updateInventory();
		}
		if (kit.equals(Kits.THOR)) {
			inv.setItem(1, ItemBuilder.criarItem(Material.WOOD_AXE, String.valueOf(Strings.primaryColor) + "Thor"));
			player.updateInventory();
		}
		if (kit.equals(Kits.SWITCHER)) {
			inv.setItem(1,
					ItemBuilder.criarItem(Material.SNOW_BALL, String.valueOf(Strings.primaryColor) + "Switcher", 16));
			player.updateInventory();
		}
		if (kit.equals(Kits.GLADIATOR)) {
			inv.setItem(1,
					ItemBuilder.criarItem(Material.IRON_FENCE, String.valueOf(Strings.primaryColor) + "Gladiator"));
			inv.setItem(2, ItemBuilder.criarItem(Material.COBBLESTONE, "�aBlocos", 16));
			player.updateInventory();
		}
		if (kit.equals(Kits.STOMPER)) {
			player.updateInventory();
		}
		if (kit.equals(Kits.GRANDPA)) {
			inv.setItem(1, ItemBuilder.criarItem(Material.STICK, String.valueOf(Strings.primaryColor) + "Grandpa",
					Enchantment.KNOCKBACK, 2));
			player.updateInventory();
		}
		if (kit.equals(Kits.SPECIALIST)) {
			inv.setItem(1, ItemBuilder.criarItem(Material.BOOK, String.valueOf(Strings.primaryColor) + "Specialist"));
			player.updateInventory();
		}
	}

	public static boolean hasKitPermission(final Player player, final Kits kit) {
		if (kit.equals(Kits.PVP)) {
			return true;
		}
		if (kit.equals(Kits.ARCHER)) {
			return true;
		}
		if (kit.equals(Kits.SPECIALIST)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.ANCHOR)) {
			return true;
		}
		if (kit.equals(Kits.KANGAROO)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.FISHERMAN)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.MONK)) {
			return true;
		}
		if (kit.equals(Kits.NINJA)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.PHANTOM)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.VIPER)) {
			return ExtremePvP.getInstance().getPermissions().isBeta(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.SNAIL)) {
			return ExtremePvP.getInstance().getPermissions().isBeta(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.HULK)) {
			return ExtremePvP.getInstance().getPermissions().isBeta(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.THOR)) {
			return ExtremePvP.getInstance().getPermissions().isBeta(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.SWITCHER)) {
			return ExtremePvP.getInstance().getPermissions().isBeta(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.GLADIATOR)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.STOMPER)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.MAGMA)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.GRANDPA)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		if (kit.equals(Kits.TURTLE)) {
			return ExtremePvP.getInstance().getPermissions().isLight(player) || ExtremePvP.fullkit;
		}
		return kit.equals(Kits.ANTISTOMPER)
				&& (ExtremePvP.getInstance().getPermissions().isBeta(player) || ExtremePvP.fullkit);
	}

	public static boolean hasAllKits(final Player player) {
		return ExtremePvP.getInstance().getPermissions().isPro(player);
	}
}
