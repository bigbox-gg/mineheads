package gg.bigbox.minecraft.plugins.mineheads.minestom.providers.minecraftheads;

import gg.bigbox.minecraft.plugins.mineheads.api.HeadCategory;
import lombok.Getter;

public enum MinecraftHeadsCategories implements HeadCategory {

    ALPHABET("alphabet", "mhp-alphabet.json"),
    ANIMALS("animals", "mhp-animals.json"),
    BLOCKS("blocks", "mhp-blocks.json"),
    DECORATION("decoration", "mhp-decoration.json"),
    FOOD_DRINKS("food-drinks", "mhp-food_drinks.json"),
    HUMANS("humans", "mhp-humans.json"),
    HUMANOID("humanoid", "mhp-humanoid.json"),
    MISCELLANEOUS("miscellaneous", "mhp-miscellaneous.json"),
    MONSTERS("monsters", "mhp-monsters.json"),
    PLANTS("plants", "mhp-plants.json");

    @Getter
    private final String name;

    public final String fileName;

    MinecraftHeadsCategories(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }
}
