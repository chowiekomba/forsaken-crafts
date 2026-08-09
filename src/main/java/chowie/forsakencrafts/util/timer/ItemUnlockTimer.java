package chowie.forsakencrafts.util.timer;

import chowie.forsakencrafts.util.Helpers;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import org.jspecify.annotations.NonNull;


public class ItemUnlockTimer implements ServerTickEvents.EndTick {
    public static ItemUnlockTimer INSTANCE = new ItemUnlockTimer();
    // magic numbers :( 5-minute timer
    private final int START_TIME = 300 * 20;
    private int time = START_TIME;

    @Override
    public void onEndTick(@NonNull MinecraftServer server) {
        time--;
        if (time == 0) {
            server.getPlayerList().getPlayers().forEach(Helpers::giveValidRandomItem);
            time = START_TIME;
        }
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(INSTANCE);
    }
}