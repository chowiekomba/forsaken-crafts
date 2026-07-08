package chowie.forsakencrafts.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class CommandRegistry {
    public static void commandRegister(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context,
                                       Commands.CommandSelection selection) {
        dispatcher.register(
                Commands.literal("unlockedblocks")
                        .executes(ModCommands::unlockedBlocks)
        );
    }
}
