package chowie.forsakencrafts.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.permissions.Permissions;

public class CommandRegistry {
    public static void commandRegister(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context,
                                       Commands.CommandSelection selection) {
        dispatcher.register(
                Commands.literal("unlocked_blocks")
                        .executes(ModCommands::unlockedBlocks)
        );
        dispatcher.register(
                Commands.literal("give_unlocked_item")
                        .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_OWNER))
                                .requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_GAMEMASTER))
                                .executes(ModCommands::giveUnlockedItem)
        );
    }
}
