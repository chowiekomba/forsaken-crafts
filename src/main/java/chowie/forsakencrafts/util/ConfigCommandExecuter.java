package chowie.forsakencrafts.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.PermissionSet;

public class ConfigCommandExecuter {
    public static void runCommand(MinecraftServer server, String command, ServerPlayer player) {
        command = command.replace("%player%", player.getGameProfile().name());

        CommandSourceStack source = server.createCommandSourceStack()
                .withPermission(PermissionSet.ALL_PERMISSIONS);

        server.getCommands().performPrefixedCommand(source, command);
    }
}
