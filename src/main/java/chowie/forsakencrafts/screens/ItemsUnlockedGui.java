package chowie.forsakencrafts.screens;

import chowie.forsakencrafts.util.ModDataAttachments;
import eu.pb4.sgui.api.gui.SimpleGui;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.LinkedList;
import java.util.List;

public class ItemsUnlockedGui {
    int page = 0;
    int itemsPerPage = 45;
    int totalPages;
    int start;
    int end;
    List<Item> list;

    public static ItemsUnlockedGui INSTANCE = new ItemsUnlockedGui();

    public void openGui(ServerPlayer player, int page) {
        this.page = page;
        this.list = new LinkedList<>(player.getAttachedOrCreate(ModDataAttachments.ITEMS_UNLOCKED));
        SimpleGui gui = new SimpleGui(MenuType.GENERIC_9x6, player, false);
        gui.setTitle(Component.literal("Unlocked Items"));
        update();

        if (list.size() > 1) {
            int slot = 0;
            for (Item item : list.subList(start, end)) {
                gui.setSlot(slot++, item.getDefaultInstance());
            }
        }

        if (page > 0) {
            ItemStack leftButton = new ItemStack(Items.ARROW);
            leftButton.set(DataComponents.CUSTOM_NAME, Component.literal("§ePrevious Page"));
            gui.setSlot(45, leftButton, clickType -> {
                if (clickType.isLeft) {
                    openGui(player, page - 1);
                }
            });
        }

        if (totalPages > page + 1) {
            ItemStack rightButton = new ItemStack(Items.ARROW);
            rightButton.set(DataComponents.CUSTOM_NAME, Component.literal("§eNext Page"));
            gui.setSlot(53, rightButton, clickType -> {
                if (clickType.isLeft) {
                    openGui(player, page + 1);
                }
            });
        }

        gui.open();
    }

    void update() {
        list.removeIf(i -> i.equals(Items.AIR));
        totalPages = (int) Math.ceil((double) list.size() / itemsPerPage);
        start = page * itemsPerPage;
        end = Math.min(start + itemsPerPage, list.size());
    }
}
