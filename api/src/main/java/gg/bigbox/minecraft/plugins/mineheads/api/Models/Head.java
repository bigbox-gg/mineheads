package gg.bigbox.minecraft.plugins.mineheads.api.Models;

import lombok.Getter;

import java.util.List;

public class Head {
    @Getter
    protected final String name;

    @Getter
    protected final List<String> searchableBy;

    @Getter
    protected final String skinTexture;

    protected Head(String name, List<String> searchableBy, String skinTexture) {
        this.name = name;
        this.searchableBy = searchableBy;
        this.skinTexture = skinTexture;
    }
}
