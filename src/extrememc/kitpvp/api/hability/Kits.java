package extrememc.kitpvp.api.hability;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

public enum Kits {
	NENHUM(Material.STONE_SWORD, "Nenhum",
			Arrays.asList("", "§fN\u00e3o possui nenhuma", "§fhabilidade.", "", "§aClique para selecionar!"), 40000),
	PVP(Material.STONE_SWORD, "PvP", Arrays.asList("", "§fEsse kit n\u00e3o possui", "§fnenhuma habilidade,",
			"§fp\u00f3rem voc\u00ea ir\u00e1 pegar", "§fuma espada sharpness I.", "", "§aClique para selecionar!"),
			1000),
	ARCHER(Material.BOW, "Archer",
			Arrays.asList("", "§fReceba um arco e ", "§fflechas infinito.", "", "§aClique para selecionar!"), 10000),
	ANCHOR(Material.ANVIL, "Anchor",
			Arrays.asList("", "§fN\u00e3o tome e nem ", "§fd\u00ea knockback em seus", "§finimigos.", "",
					"§aClique para selecionar!"),
			5000),
	KANGAROO(Material.FIREWORK, "Kangaroo",
			Arrays.asList("", "§fPule mais alto ", "§fcom seu foguete!", "", "§aClique para selecionar!"), 40000),
	FISHERMAN(Material.FISHING_ROD, "Fisherman",
			Arrays.asList("", "§fPesque seus inimigos", "§f e os traga at\u00e9 voc\u00ea!", "",
					"§aClique para selecionar!"),
			10000),
	MONK(Material.BLAZE_ROD, "Monk",
			Arrays.asList("", "§fEmbaralhe o invent\u00e1rio ", "§fde seus inimigos!", "", "§aClique para selecionar!"),
			7000),
	NINJA(Material.COMPASS, "Ninja",
			Arrays.asList("", "§fTeleporte-se at\u00e9 ", "§fseus inimigos!", "", "§aClique para selecionar!"), 10000),
	PHANTOM(Material.FEATHER, "Phantom",
			Arrays.asList("", "§fTenha a habilidade de ", "§fvoo por 5 segundos!", "", "§aClique para selecionar!"),
			8000),
	VIPER(Material.SPIDER_EYE, "Viper",
			Arrays.asList("", "§fEnvenene seus ", "§finimigos!", "", "§aClique para selecionar!"), 3000),
	SNAIL(Material.STRING, "Snail",
			Arrays.asList("", "§fDeixe seus inimigos ", "§fmais lerdos!", "", "§aClique para selecionar!"), 3000),
	HULK(Material.SADDLE, "Hulk",
			Arrays.asList("", "§fCarregue seus inimigos ", "§fnas costas!", "", "§aClique para selecionar!"), 5000),
	THOR(Material.WOOD_AXE, "Thor",
			Arrays.asList("", "§fBote fogo em seus ", "§foponentes com um", "§fraio!", "", "§aClique para selecionar!"),
			5000),
	SWITCHER(Material.SNOW_BALL, "Switcher",
			Arrays.asList("", "§fTroque de lugar ", "§fcom seu oponente!", "", "§aClique para selecionar!"), 2000),
	GLADIATOR(Material.IRON_FENCE, "Gladiator",
			Arrays.asList("", "§fConvoque seus oponentes", "§fpara uma batalha", "§fnos ares!", "",
					"§aClique para selecionar!"),
			10000),
	STOMPER(Material.IRON_BOOTS, "Stomper",
			Arrays.asList("", "§fPositeie seus ", "§finimigos!", "", "§aClique para selecionar!"), 8000),
	MAGMA(Material.FIRE, "Magma",
			Arrays.asList("", "§fBote fogo em seus ", "§foponentes!", "", "§aClique para selecionar!"), 10000),
	GRANDPA(Material.STICK, "Grandpa",
			Arrays.asList("", "§fJogue seus inimigos", "§fpara longe!", "", "§aClique para selecionar!"), 2000),
	ANTISTOMPER(Material.DIAMOND_BOOTS, "AntiStomper",
			Arrays.asList("", "§fN\u00e3o seja pisotiado", "§fpor stomper's.", "", "§aClique para selecionar!"), 10000),
	TURTLE(Material.BEACON, "Turtle",
			Arrays.asList("", "§fN\u00e3o leve dano de", "§fqueda ao cair.", "", "§aClique para selecionar!"), 2000),
	SPECIALIST(Material.BOOK, "Specialist", Arrays.asList("", "§fAo matar seus", "§finimigos ganhar\u00e1 1XP",
			"§fpara encantar seus", "§fequipamentos!", "", "§aClique para selecionar!"), 2000);

	private Material material;
	private String name;
	private List<String> lore;
	private int price;

	Kits(final Material material, final String name, final List<String> lore, final int price) {
		this.material = material;
		this.name = name;
		this.lore = lore;
		this.price = price;
	}

	public Material getMaterial() {
		return this.material;
	}

	public String getName() {
		return this.name;
	}

	public List<String> getLore() {
		return this.lore;
	}

	public int getPrice() {
		return this.price;
	}
}
