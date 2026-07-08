package chowie.forsakencrafts.commands;

import chowie.forsakencrafts.screens.ItemsUnlockedGui;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;

public class ModCommands {
    public static int unlockedBlocks(CommandContext<CommandSourceStack> commandContext) {
        if (commandContext.getSource().getEntity() instanceof ServerPlayer player) {
            ItemsUnlockedGui.INSTANCE.openGui(player, 0);
        }
        return 1;
    }
}
