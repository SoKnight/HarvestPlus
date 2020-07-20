package ru.soknight.harvestplus;

import org.bukkit.plugin.java.JavaPlugin;

import ru.soknight.harvestplus.command.CommandHarvestplus;
import ru.soknight.harvestplus.listener.CropHarvestListener;
import ru.soknight.lib.configuration.Configuration;
import ru.soknight.lib.configuration.Messages;

public class HarvestPlus extends JavaPlugin {

	private Configuration config;
	private Messages messages;
	
	@Override
	public void onEnable() {
		
		// Configs initialization
		refreshConfigs();
				
		// Commands executors initialization
		registerCommands();
				
		// Event listeners initialization
		registerListeners();
		
	}
	
	private void refreshConfigs() {
		this.config = new Configuration(this, "config.yml");
		config.refresh();
		
		this.messages = new Messages(this, "messages.yml");
		messages.refresh();
	}
	
	private void registerCommands() {
		new CommandHarvestplus(this, messages);
	}
	
	private void registerListeners() {
		new CropHarvestListener(this, config);
	}
	
	public void refresh() {
		config.refresh();
		messages.refresh();
		
		registerCommands();
	}


}
