package gg.bigbox.minecraft.plugins.mineheads.api.MinecraftHeads;

import gg.bigbox.minecraft.plugins.mineheads.api.Models.Head;

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
    public String getId() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getSearchableBy() {
        return tags;
    }

    @Override
    public String getSkinData() {
        return value;
    }
}
