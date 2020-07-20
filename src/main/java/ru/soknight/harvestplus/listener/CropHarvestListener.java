package ru.soknight.harvestplus.listener;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import lombok.AllArgsConstructor;
import ru.soknight.lib.configuration.Configuration;

@AllArgsConstructor
public class CropHarvestListener implements Listener {

	private static final Map<Material, Material> TYPES = new HashMap<>();
	
	private final Configuration config;
	
	public CropHarvestListener(Plugin plugin, Configuration config) {
		this.config = config;
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCropHarvest(PlayerInteractEvent event) {
		
		if(!config.getBoolean("enabled")) return;
		
		Action action = event.getAction();
		if(action != Action.RIGHT_CLICK_BLOCK) return;
		
		EquipmentSlot hand = event.getHand();
		if(hand != EquipmentSlot.HAND) return;
		
		Player player = event.getPlayer();
		if(player.isSneaking()) return;
		
		Block block = event.getClickedBlock();
		
		Material type = block.getType();
		if(!TYPES.containsKey(type)) return;
		
		// Drop filtering
		
		Collection<ItemStack> drop = block.getDrops();
				
		drop = drop.parallelStream()
				.filter(i -> {
					if(i.getType() != TYPES.get(type)) return true;
							
					int amount = i.getAmount() - 1;
					if(amount <= 0) return false;
							
					i.setAmount(amount);
					return true;
				}).collect(Collectors.toList());
		
		// Ageable BlockData reset
		
		BlockData data = block.getBlockData();
		Ageable ageable = (Ageable) data;
		
		if(ageable.getAge() != ageable.getMaximumAge()) return;
		ageable.setAge(0);
				
		block.setBlockData(ageable, true);
		
		// Dropping items
		
		Location location = block.getLocation().add(0.5D, 0.3D, 0.5D);
		World world = location.getWorld();
		
		drop.forEach(i -> world.dropItem(location, i));
		
	}
	
	static {
		TYPES.put(Material.WHEAT, Material.WHEAT_SEEDS);
		TYPES.put(Material.POTATOES, Material.POTATO);
		TYPES.put(Material.CARROTS, Material.CARROT);
		TYPES.put(Material.BEETROOTS, Material.BEETROOT_SEEDS);
		TYPES.put(Material.NETHER_WART, Material.NETHER_WART);
	}
	
}
