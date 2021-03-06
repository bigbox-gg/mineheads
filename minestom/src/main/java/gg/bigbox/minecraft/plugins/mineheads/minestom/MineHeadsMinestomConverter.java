package gg.bigbox.minecraft.plugins.mineheads.minestom;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.PlayerHeadMeta;
import net.minestom.server.tag.Tag;

import java.util.List;
import java.util.Optional;

public class MineHeadsMinestomConverter {

    private static final Tag<String> mineHeadsIdTag = Tag.String("mineheads-head");
    private static final Tag<String> mineHeadsProviderTag = Tag.String("mineheads-head-provider");

    public PlayerHeadMeta playerHead(Head head) {
        return new PlayerHeadMeta.Builder()
                .playerSkin(
                        new PlayerSkin(head.getSkinData(), "")
                )
                .skullOwner(head.getSkullUUID())
                .build();
    }

    public ItemStack playerItemStack(Head head) {
        ItemStack itemStack = ItemStack.of(Material.PLAYER_HEAD);

        itemStack = itemStack
                .withMeta(playerHead(head))
                .withAmount(1)
                .withDisplayName(Component.text(head.getName()))
                .withTag(mineHeadsIdTag, head.getId())
                .withTag(mineHeadsProviderTag, head.getProviderName())
                .withLore(List.of(
                        Component.text("Tags: " + head.getSearchableBy()),
                        Component.empty(),
                        Component.text("Provider: " + head.getProviderName())
                ));

        return itemStack;
    }

    public boolean isHead(ItemStack itemStack) {
        return itemStack.hasTag(mineHeadsIdTag) && itemStack.hasTag(mineHeadsProviderTag);
    }

    public Optional<Head> headFromItemStack(List<Head> heads, ItemStack itemStack) {
        if (!isHead(itemStack)) {
            return Optional.empty();
        }

        return heads.stream()
                .filter(
                        head -> Utils.stringMatch(head.getId(), itemStack.getTag(mineHeadsIdTag))
                                && Utils.stringMatch(head.getProviderName(), itemStack.getTag(mineHeadsProviderTag))
                )
                .findFirst();
    }

}
