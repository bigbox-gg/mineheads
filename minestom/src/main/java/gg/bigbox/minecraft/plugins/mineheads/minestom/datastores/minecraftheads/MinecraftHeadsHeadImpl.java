package gg.bigbox.minecraft.plugins.mineheads.minestom.datastores.minecraftheads;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MinecraftHeadsHeadImpl implements Head {

    private String name;

    private String uuid;

    private List<String> tags;

    private String value;

    public MinecraftHeadsHeadImpl() {
        // empty constructor for gson
    }


    @Override
    public @NotNull String getId() {
        return uuid;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull List<String> getSearchableBy() {
        return tags;
    }

    @Override
    public @NotNull String getSkinData() {
        return value;
    }

    @Override
    public @NotNull String getProviderName() {
        return "minecraft-heads.com";
    }
}
