package gg.bigbox.minecraft.plugins.mineheads.minestom;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.PlayerHeadMeta;
import net.minestom.server.tag.Tag;

import java.util.List;

public class MineHeadsMinestomConverter {

    private static final Tag<String> mineHeadsIdTag = Tag.String("mineheads-head");

    public PlayerHeadMeta playerHead(Head head) {
        return new PlayerHeadMeta.Builder()
                .playerSkin(
                        new PlayerSkin(head.getSkinData(), "")
                )
                .build();
    }

    public ItemStack playerItemStack(Head head) {
        ItemStack itemStack = ItemStack.of(Material.PLAYER_HEAD);

        itemStack = itemStack
                .withMeta(playerHead(head))
                .withAmount(1)
                .withDisplayName(Component.text(head.getName()))
                .withTag(mineHeadsIdTag, head.getId())
                .withLore(List.of(
                        Component.text(head.getName()),
                        Component.newline(),
                        Component.text(head.getSearchableBy().toString()),
                        Component.newline(),
                        Component.text(head.getProviderName())
                ));

        return itemStack;
    }

    public boolean isHead(ItemStack itemStack) {
        return itemStack.hasTag(mineHeadsIdTag);
    }

}