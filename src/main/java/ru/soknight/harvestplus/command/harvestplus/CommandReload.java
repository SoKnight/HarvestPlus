package ru.soknight.harvestplus.command.harvestplus;

import org.bukkit.command.CommandSender;

import ru.soknight.harvestplus.HarvestPlus;
import ru.soknight.lib.argument.CommandArguments;
import ru.soknight.lib.command.preset.subcommand.PermissibleSubcommand;
import ru.soknight.lib.configuration.Messages;

public class CommandReload extends PermissibleSubcommand {

	private final HarvestPlus plugin;
	private final Messages messages;
	
	public CommandReload(HarvestPlus plugin, Messages messages) {
		super("harvestplus.command.reload", messages);
		
		this.plugin = plugin;
		this.messages = messages;
		
	}

	@Override
	protected void executeCommand(CommandSender sender, CommandArguments args) {
		
		plugin.refresh();
		
		messages.getAndSend(sender, "reload-success");
		
	}

}
