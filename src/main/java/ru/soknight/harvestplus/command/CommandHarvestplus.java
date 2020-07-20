package ru.soknight.harvestplus.command;

import ru.soknight.harvestplus.HarvestPlus;
import ru.soknight.harvestplus.command.harvestplus.CommandHelp;
import ru.soknight.harvestplus.command.harvestplus.CommandReload;
import ru.soknight.lib.command.preset.ModifiedDispatcher;
import ru.soknight.lib.configuration.Messages;

public class CommandHarvestplus extends ModifiedDispatcher {

	public CommandHarvestplus(HarvestPlus plugin, Messages messages) {
		super("harvestplus", messages);
		
		super.setExecutor("help", new CommandHelp(messages));
		super.setExecutor("reload", new CommandReload(plugin, messages));
		
		super.register(plugin, true);
		
	}

}
