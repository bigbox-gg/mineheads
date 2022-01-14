package gg.bigbox.minecraft.plugins.mineheads.api;

import java.util.List;

public interface Head {

    String getId();

    String getName();

    List<String> getSearchableBy();

    String getSkinData();

}
