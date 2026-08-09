package chowie.forsakencrafts;

import chowie.forsakencrafts.commands.CommandRegistry;
import chowie.forsakencrafts.util.timer.ItemDisplayTimer;
import chowie.forsakencrafts.util.ModConfig;
import chowie.forsakencrafts.util.ModDataAttachments;
import chowie.forsakencrafts.util.timer.ItemUnlockTimer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForsakenCrafts implements ModInitializer {
	public static final String MOD_ID = "forsaken-crafts";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ModConfig CONFIG = ModConfig.createToml(
			FabricLoader.getInstance().getConfigDir(),
			"","ForsakenCraftsConfig", ModConfig.class);

	@Override
	public void onInitialize() {
		LOGGER.info("Registering {}", MOD_ID);

		ModDataAttachments.register();
		ItemDisplayTimer.register();
		ItemUnlockTimer.register();

		CommandRegistrationCallback.EVENT.register(CommandRegistry::commandRegister);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
