package ru.soknight.harvestplus.command.harvestplus;

import ru.soknight.lib.command.enhanced.help.command.EnhancedHelpExecutor;
import ru.soknight.lib.command.response.CommandResponseType;
import ru.soknight.lib.configuration.Messages;

public class CommandHelp extends EnhancedHelpExecutor {

	public CommandHelp(Messages messages) {
		super(messages);
		
		super.setHeaderFrom("help.header");
		super.setFooterFrom("help.footer");
		
		super.factory()
				.helpLineFormatFrom("help.body")
				.permissionFormat("harvestplus.command.%s")
				
				.newLine()
					.command("help", true)
					.add()
				.newLine()
					.command("reload", true)
					.add();
		
		super.completeMessage();
		
		super.setPermission("harvestplus.command.help");
		super.setResponseMessageByKey(CommandResponseType.NO_PERMISSIONS, "error.no-permissions");
		
	}

}
