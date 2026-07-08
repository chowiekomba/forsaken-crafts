package chowie.forsakencrafts.util;

import folk.sisby.kaleido.api.WrappedConfig;
import folk.sisby.kaleido.lib.quiltconfig.api.annotations.Comment;

public class ModConfig extends WrappedConfig {
    @Comment("The current supported substitutions are: ")
    @Comment("%player% -> the current player that does the action")
    @Comment("----------------------------------------------------")
    @Comment("----------------------------------------------------")
    @Comment("This command gets ran when a player unlocks an item. Leave empty if you don't want a command to be ran")
    @Comment("Leave empty if you don't want a command to be ran")
    @Comment("----------------------------------------------------")
    @Comment("----------------------------------------------------")
    public String unlockCommand = "/effect give %player% minecraft:invisibility 600";
    @Comment("----------------------------------------------------")
    @Comment("----------------------------------------------------")
    @Comment("This command is ran when a player picks up an item that isn't already unlocked")
    @Comment("Leave empty if you don't want a command to be ran")
    @Comment("This command runs every time a player picks up a locked item, while the first command is only ran once")
    @Comment("for every item not unlocked")
    @Comment("----------------------------------------------------")
    @Comment("----------------------------------------------------")
    public String pickUpCommand = "/effect give %player% minecraft:nausea 600";
}
