package chowie.forsakencrafts.util.timer;

import chowie.forsakencrafts.util.PlayerItem;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ItemDisplayTimer implements ServerTickEvents.EndTick {
    public static ItemDisplayTimer INSTANCE = new ItemDisplayTimer();
    private final Map<PlayerItem, Integer> playerMap = new HashMap<>();

    public void setTimer(ServerPlayer player, Item item, int ticks) {
        playerMap.put(new PlayerItem(player, item), ticks);
    }

    @Override
    public void onEndTick(@NonNull MinecraftServer server) {
        for (PlayerItem playerItem : Set.copyOf(playerMap.keySet())) {
            if (playerMap.put(playerItem, playerMap.getOrDefault(playerItem, 0) - 1) instanceof Integer i) {
                ServerPlayer player = playerItem.player();

                if (server.getPlayerList().getPlayer(player.getUUID()) == null) {
                    playerMap.remove(playerItem);
                    continue;
                }

                if (i % 20 == 0 && i != 0) {
                    Optional<Holder.Reference<Item>> randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
                    randomItem.ifPresent(
                            itemReference -> playerItem.player().connection.send(new ClientboundSetTitleTextPacket(
                                    itemReference.value().getDefaultInstance()
                                            .getItemName().copy().withColor(TextColor.YELLOW)
                            )));
                    playerItem.player().connection.send(new ClientboundSoundPacket(
                            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.EXPERIENCE_ORB_PICKUP),
                            SoundSource.PLAYERS,
                            player.getX(), player.getY(), player.getZ(),
                            1.0f, 0.6f,
                            player.level().getSeed()
                    ));
                }

                if (i == 0) {
                    playerMap.remove(playerItem);
                    playerItem.player().connection.send(new ClientboundSetTitleTextPacket(
                            playerItem.item().getDefaultInstance().getItemName().copy().withColor(TextColor.GREEN)
                    ));
                    playerItem.player().connection.send(new ClientboundSoundPacket(
                            BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.PLAYER_LEVELUP),
                            SoundSource.PLAYERS,
                            player.getX(), player.getY(), player.getZ(),
                            1.0f, 0.5f,
                            player.level().getSeed()
                    ));
                }
            }
        }
    }

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(INSTANCE);
    }
}
