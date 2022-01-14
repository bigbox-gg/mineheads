package gg.bigbox.minecraft.plugins.mineheads.minestom.datastores.minecraftheads;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gg.bigbox.minecraft.plugins.mineheads.api.Head;
import gg.bigbox.minecraft.plugins.mineheads.api.MineHeadsDatastore;
import gg.bigbox.minecraft.plugins.mineheads.api.events.MineHeadsDatabaseRefreshedEvent;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;

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

public class MinecraftHeadsProvider implements MineHeadsDatastore {

    private final EventNode<Event> eventNode;

    private final Path dataPath;

    private final Logger logger = Logger.getLogger("MineHeads - MinecraftHeadsProvider");

    private final ArrayList<Head> heads = new ArrayList<>(45000);

    private final Gson gson = new Gson();

    public MinecraftHeadsProvider(EventNode<Event> node, Path dataPath) {
        this.eventNode = node;
        this.dataPath = dataPath;
    }

    @Override
    public ArrayList<Head> getHeads() {
        if (heads.isEmpty()) {
            downloadHeads();
        }

        return heads;
    }

    @Override
    public void refresh() {
        downloadHeads();
    }

    public void downloadHeads() {
        // Clear all the present heads
        heads.clear();

        for (MinecraftHeadsCategories category : MinecraftHeadsCategories.values()) {
            // Download the category
            if (!downloadCategory(category)) {
                logger.log(Level.WARNING, "Unable to download category {} from minecraftheads api.", category.getName());

                continue;
            }

            // Add it to heads list
            heads.addAll(
                    loadCategory(category)
                            .stream()
                            .map(head -> head.setCategoryName(category.getName()))
                            .toList()
            );
        }

        // Advise every listener that the database has been refreshed.
        eventNode.call(new MineHeadsDatabaseRefreshedEvent());
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
                    String.format("https://minecraft-heads.com/scripts/api.php?cat=%s&tags=true", category.getName())
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

    /**
     * Load a category from the file.
     * Can return an empty list of an error
     * has been encountered.
     *
     * @param category The category to load from the file.
     * @return The list of heads of this category, or an empty list if there is an error.
     */
    public List<Head> loadCategory(MinecraftHeadsCategories category) {
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
