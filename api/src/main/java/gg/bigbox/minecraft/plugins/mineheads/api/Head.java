package gg.bigbox.minecraft.plugins.mineheads.api;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Head {

    @NotNull String getId();

    @NotNull String getName();

    @NotNull String getCategoryName();

    Head setCategoryName(@NotNull String name);

    @NotNull List<String> getSearchableBy();

    @NotNull String getSkinData();

    @NotNull String getProviderName();

}
