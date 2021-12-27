package gg.bigbox.minecraft.plugins.mineheads.api;

import gg.bigbox.minecraft.plugins.mineheads.api.Models.Head;

public interface MineHeadsConverter<T> {

    T convertHead(Head head);

}
