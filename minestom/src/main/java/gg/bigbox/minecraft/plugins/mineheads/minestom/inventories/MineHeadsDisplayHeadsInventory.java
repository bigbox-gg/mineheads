package gg.bigbox.minecraft.plugins.mineheads.minestom.inventories;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.Pagination;
import fr.minuskube.inv.content.SlotIterator;
import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeads;
import gg.bigbox.minecraft.plugins.mineheads.minestom.MineHeadsMinestomImpl;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.List;

public record MineHeadsDisplayHeadsInventory(
        MineHeads extension,
        List<Head> heads
) implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        Pagination pagination = contents.pagination();

        ClickableItem[] items = new ClickableItem[heads.size()];

        for (int i = 0; i < items.length; i++) {
            int finalI = i;
            items[i] = ClickableItem.of(
                    extension.getItemStack(heads.get(i)),
                    inventoryPreClickEvent -> player.setItemInMainHand(extension.getItemStack(heads.get(finalI)))
            );
        }

        pagination.setItems(items);
        pagination.setItemsPerPage(45);

        pagination.addToIterator(contents.newIterator(SlotIterator.Type.HORIZONTAL, 0, 0));

        contents.set(5, 3, ClickableItem.of(ItemStack.of(Material.ARROW),
                e -> INVENTORY(extension, heads).open(player, pagination.previous().getPage())));
        contents.set(5, 5, ClickableItem.of(ItemStack.of(Material.ARROW),
                e -> INVENTORY(extension, heads).open(player, pagination.next().getPage())));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        InventoryProvider.super.update(player, contents);
    }

    public static SmartInventory INVENTORY(MineHeads extension, List<Head> heads) {
        return SmartInventory.builder()
                .manager(MineHeadsMinestomImpl.getInventoryManager())
                .id("MineHeadsDisplayHeadsInventory")
                .size(6, 9)
                .title("MineHeads - Search Result")
                .closeable(true)
                .provider(new MineHeadsDisplayHeadsInventory(extension, heads))
                .type(InventoryType.CHEST_6_ROW)
                .build();
    }
}
