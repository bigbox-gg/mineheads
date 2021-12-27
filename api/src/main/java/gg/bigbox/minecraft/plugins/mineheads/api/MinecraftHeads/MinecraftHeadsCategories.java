package gg.bigbox.minecraft.plugins.mineheads.api.MinecraftHeads;

public enum MinecraftHeadsCategories {

    ALPHABET("alphabet", "alphabet.json"),
    ANIMALS("animals", "animals.json"),
    BLOCKS("blocks", "blocks.json"),
    DECORATION("decoration", "decoration.json"),
    FOOD_DRINKS("food-drinks", "food_drinks.json"),
    HUMANS("humans", "humans.json"),
    HUMANOID("humanoid", "humanoid.json"),
    MISCELLANEOUS("miscellanous", "miscellanous.json"),
    MONSTERS("monsters", "monsters.json"),
    PLANTS("plants", "plants.json");

    public final String apiCategoryName;

    public final String fileName;

    MinecraftHeadsCategories(String apiCategoryName, String fileName) {
        this.apiCategoryName = apiCategoryName;
        this.fileName = fileName;
    }

}
