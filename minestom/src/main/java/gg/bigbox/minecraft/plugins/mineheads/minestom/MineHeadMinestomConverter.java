package gg.bigbox.minecraft.plugins.mineheads.minestom;

import gg.bigbox.minecraft.plugins.mineheads.api.MineHeadsConverter;
import gg.bigbox.minecraft.plugins.mineheads.api.Models.HeadImpl;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.metadata.PlayerHeadMeta;

public class MineHeadMinestomConverter implements MineHeadsConverter<PlayerHeadMeta> {

    @Override
    public PlayerHeadMeta convertHead(HeadImpl head) {
        return new PlayerHeadMeta.Builder()
                .playerSkin(
                        new PlayerSkin(head.getSkinTexture(), "")
                )
                .build();
    }

}
