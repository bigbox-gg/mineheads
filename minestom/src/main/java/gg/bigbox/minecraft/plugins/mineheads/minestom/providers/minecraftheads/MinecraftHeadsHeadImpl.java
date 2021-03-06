package gg.bigbox.minecraft.plugins.mineheads.minestom.providers.minecraftheads;

import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MinecraftHeadsHeadImpl implements Head {

    private String name;

    private String uuid;

    private String tags;

    private String value;

    private String categoryName = "";

    public MinecraftHeadsHeadImpl() {
        // empty constructor for gson
    }


    @Override
    public @NotNull String getId() {
        return uuid;
    }

    @Override
    public @NotNull UUID getSkullUUID() {
        return UUID.fromString(uuid);
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public @NotNull String getCategoryName() {
        return categoryName;
    }

    @Override
    public Head setCategoryName(@NotNull String name) {
        this.categoryName = name;

        return this;
    }

    @Override
    public @NotNull List<String> getSearchableBy() {
        if (tags == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(tags.split(",")).toList();
    }

    @Override
    public @NotNull String getSkinData() {
        return value;
    }

    @Override
    public @NotNull String getProviderName() {
        return "minecraft-heads.com";
    }

    @Override
    public String toString() {
        return "MinecraftHeadsHeadImpl{" +
                "name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                ", tags='" + tags + '\'' +
                ", value='" + value + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
