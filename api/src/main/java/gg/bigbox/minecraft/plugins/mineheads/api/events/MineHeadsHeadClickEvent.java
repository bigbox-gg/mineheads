package gg.bigbox.minecraft.plugins.mineheads.api.events;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.item.ItemStack;

public record MineHeadsHeadClickEvent(
        Player player,
        ItemStack itemStack,
        Head head
) implements Event {
}
