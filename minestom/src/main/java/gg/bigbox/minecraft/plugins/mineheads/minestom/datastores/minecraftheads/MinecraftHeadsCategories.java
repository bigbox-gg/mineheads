package gg.bigbox.minecraft.plugins.mineheads.minestom.datastores.minecraftheads;

import gg.bigbox.minecraft.plugins.mineheads.api.HeadCategory;
import lombok.Getter;

public enum MinecraftHeadsCategories implements HeadCategory {

    ALPHABET("alphabet", "alphabet.json"),
    ANIMALS("animals", "animals.json"),
    BLOCKS("blocks", "blocks.json"),
    DECORATION("decoration", "decoration.json"),
    FOOD_DRINKS("food-drinks", "food_drinks.json"),
    HUMANS("humans", "humans.json"),
    HUMANOID("humanoid", "humanoid.json"),
    MISCELLANEOUS("miscellaneous", "miscellaneous.json"),
    MONSTERS("monsters", "monsters.json"),
    PLANTS("plants", "plants.json");

    @Getter
    private final String name;

    public final String fileName;

    MinecraftHeadsCategories(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }
}
