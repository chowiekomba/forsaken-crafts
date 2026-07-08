package chowie.forsakencrafts;

import chowie.forsakencrafts.commands.CommandRegistry;
import chowie.forsakencrafts.util.ItemDisplayTimer;
import chowie.forsakencrafts.util.ModDataAttachments;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForsakenCrafts implements ModInitializer {
	public static final String MOD_ID = "forsaken-crafts";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Registering {}", MOD_ID);

		ModDataAttachments.register();
		ItemDisplayTimer.register();

		CommandRegistrationCallback.EVENT.register(CommandRegistry::commandRegister);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
