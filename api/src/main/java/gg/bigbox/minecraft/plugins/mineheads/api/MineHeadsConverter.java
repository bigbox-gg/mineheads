package gg.bigbox.minecraft.plugins.mineheads.api;

import gg.bigbox.minecraft.plugins.mineheads.api.Models.HeadImpl;

public interface MineHeadsConverter<T> {

    T convertHead(HeadImpl head);

}
