package gg.bigbox.minecraft.plugins.mineheads.minestom.providers.minecraftheads;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.HeadCategory;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeadsHeadProvider;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MinecraftHeadsProvider implements MineHeadsHeadProvider {

    private final Path dataPath;

    private final Logger logger = Logger.getLogger("MineHeads.MinecraftHeadsProvider");

    private final Gson gson = new Gson();

    public MinecraftHeadsProvider(Path dataPath) {
        this.dataPath = dataPath;
    }

    @Override
    public @NotNull ArrayList<Head> getHeads() {
        ArrayList<Head> heads = new ArrayList<>(45000);

        for (MinecraftHeadsCategories category : MinecraftHeadsCategories.values()) {
            // Add it to heads list
            heads.addAll(
                    loadCategory(category)
                            .stream()
                            .map(head -> head.setCategoryName(category.getName()))
                            .toList()
            );
        }

        return heads;
    }

    @Override
    public @NotNull List<HeadCategory> getCategories() {
        return List.of(MinecraftHeadsCategories.values());
    }

    @Override
    public void refresh() {
        for (MinecraftHeadsCategories category : MinecraftHeadsCategories.values()) {
            // Download the category
            if (!downloadCategory(category)) {
                logger.log(Level.WARNING, "Unable to download category {} from minecraftheads api.", category.getName());
            }
        }
    }

    /**
     * Downloads a category in the datastore.
     *
     * @param category The category to download.
     * @return true if success, false otherwise.
     */
    public boolean downloadCategory(MinecraftHeadsCategories category) {
        logger.log(Level.INFO, "Downloading category {}", category.getName());

        try {
            URL downloadUrl = new URL(
                    String.format("https://minecraft-heads.com/scripts/api.php?cat=%s&tags=true", category.getName())
            );

            InputStream in = downloadUrl.openStream();

            Files.copy(in, Path.of(dataPath.toString(), category.fileName), StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (Exception e) {
            logger.log(Level.WARNING, "Unable to download category {}", category.getName());

            e.printStackTrace();
            return false;
        }
    }

    /**
     * Load a category from the file.
     * Can return an empty list of an error
     * has been encountered.
     *
     * @param category The category to load from the file.
     * @return The list of heads of this category, or an empty list if there is an error.
     */
    public List<Head> loadCategory(MinecraftHeadsCategories category) {
        logger.log(Level.INFO, "Loading category {}", category.getName());

        try {
            return gson.fromJson(
                    new BufferedReader(new FileReader(new File(dataPath.toString(), category.fileName))),
                    new TypeToken<List<MinecraftHeadsHeadImpl>>() {
                    }.getType()
            );
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
