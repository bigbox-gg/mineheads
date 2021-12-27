package gg.bigbox.minecraft.plugins.mineheads.api.MinecraftHeads;

import gg.bigbox.minecraft.plugins.mineheads.api.Models.Head;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Logger;

public class MinecraftHeadsProvider {

    private final Path dataPath;

    private final Logger logger;

    public MinecraftHeadsProvider(Path dataPath, Logger logger) {
        this.dataPath = dataPath;
        this.logger = logger;
    }

    public ArrayList<Head> refreshDatastore() {
        if (!downloadAllCategories()) {
            return new ArrayList<>(0);
        }

        // Load all the data.
        ArrayList<Head> tempData = new ArrayList<>(42000);

        // TODO
        return tempData;
    }

    public boolean downloadAllCategories() {
        for (MinecraftHeadsCategories category : MinecraftHeadsCategories.values()) {
            if (!downloadCategory(category)) {
                logger.warning("Unable to download all categories from MinecraftHeads.");
                return false;
            }
        }

        return true;
    }

    /**
     * Downloads a category in the datastore.
     *
     * @param category The category to download.
     * @return true if success, false otherwise.
     */
    public boolean downloadCategory(MinecraftHeadsCategories category) {
        try {
            URL downloadUrl = new URL(
                    String.format("https://minecraft-heads.com/scripts/api.php?cat=%s&tags=true", category.apiCategoryName)
            );

            InputStream in = downloadUrl.openStream();

            Files.copy(in, Path.of(dataPath.toString(), category.fileName), StandardCopyOption.REPLACE_EXISTING);

            return true;
        } catch (Exception e) {
            logger.warning(
                    String.format("Unable to download category %s", category.name())
            );

            e.printStackTrace();
            return false;
        }
    }

}
